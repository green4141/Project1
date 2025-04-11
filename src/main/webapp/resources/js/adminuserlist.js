function encodeBase64(str) {
    const encoder = new TextEncoder();
    const bytes = encoder.encode(str);
    let binary = '';
    for (let i = 0; i < bytes.length; i++) {
        binary += String.fromCharCode(bytes[i]);
    }
    return encodeURIComponent(btoa(binary).replace("+", "_"));
}

function decodeBase64(str) {
    try {
        const binary = atob(decodeURIComponent(str).replace("_", "+"));
        const bytes = new Uint8Array(binary.length);
        for (let i = 0; i < binary.length; i++) {
            bytes[i] = binary.charCodeAt(i);
        }
        return new TextDecoder("utf-8").decode(bytes);
    } catch (e) {
        return str;
    }
}


const searchParam = new URLSearchParams(window.location.search)

$(() => {
	if (searchParam.get("id")) {
		$("#searchfield").val("id").prop("selected", true)
		$("#search").val(decodeBase64(searchParam.get("id")))
	} else if (searchParam.get("name")) {
		$("#searchfield").val("name").prop("selected", true)
		$("#search").val(decodeBase64(searchParam.get("name")))
	} else if (searchParam.get("username")) {
		$("#searchfield").val("username").prop("selected", true)
		$("#search").val(decodeBase64(searchParam.get("username")))
	} else if (searchParam.get("search_role")) {
		$("#searchfield").val("search_role").prop("selected", true)
		$("#search").hide()
		$("#search_role").val(searchParam.get("search_role")).prop("selected", true).show()
	}

	$("#searchfield").on("change", () => {
		const selected = $("#searchfield option:selected").val()
		console.log(selected)
		if (selected == "search_role") {
			$("#search").hide();
			$("#search_role").show();
		} else {
			$("#search").show();
			$("#search_role").hide();
		}
	})

	$("#search-btn").on("click", () => {
		const selected = $("#searchfield option:selected").val()
		const search = $("#search").val();
		let searchquery = "";

		switch(selected) {
			case "id" : {
				searchquery = `id=${encodeBase64(search)}`
				break
			}
			case "name": {
				searchquery = `name=${encodeBase64(search)}`
				break
			}
			case "username": {
				searchquery = `username=${encodeBase64(search)}`
				break
			}
			default: {
				searchquery = `search_role=${$("#search_role option:selected").val()}`
			}
		}
		
		location.href = `/admin/user?${searchquery}`
	})
})
