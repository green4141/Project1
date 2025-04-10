const searchParam = new URLSearchParams(window.location.search);


function encodeBase64(str) {
    const encoder = new TextEncoder();
    const bytes = encoder.encode(str);
    let binary = '';
    for (let i = 0; i < bytes.length; i++) {
        binary += String.fromCharCode(bytes[i]);
    }
    return encodeURIComponent(btoa(binary));
}

function decodeBase64(str) {
    try {
        const binary = atob(decodeURIComponent(str));
        const bytes = new Uint8Array(binary.length);
        for (let i = 0; i < binary.length; i++) {
            bytes[i] = binary.charCodeAt(i);
        }
        return new TextDecoder("utf-8").decode(bytes);
    } catch (e) {
        return str;
    }
}


$(() => {
	if (searchParam.get("title")) {
		const title = decodeBase64(searchParam.get("title"));
		$("#searchfield").val("title").prop("selected", true);
		$("#search").val(title);
	} else if (searchParam.get("username")) {
		const username = decodeBase64(searchParam.get("username"));
		$("#searchfield").val("username").prop("selected", true);
		$("#search").val(username);
	} else if (searchParam.get("startdate")) {
		let startdate = new Date(Number(searchParam.get("startdate")));
		let enddate = new Date(Number(searchParam.get("enddate")));

		startdate.setDate(startdate.getDate() + 1);
		enddate.setDate(enddate.getDate() - 1);

		$("#searchfield").val("date").prop("selected", true);
		$("#startdate").val(startdate.toISOString().substring(0, 10)).show();
		$("#enddate").val(enddate.toISOString().substring(0, 10)).show();
		$("#search").hide();
	}

	$("#searchfield").change(() => {
		const selected = $("option:selected").val();
		if (selected == "date") {
			const $startdate = $("#startdate");
			const $enddate = $("#enddate");
			if ($startdate.val() == "") $startdate.val(new Date().toISOString().substring(0, 10));
			if ($enddate.val() == "") $enddate.val(new Date().toISOString().substring(0, 10));
			$("#startdate").show();
			$("#enddate").show();
			$("#search").hide();
		} else {
			$("#startdate").hide();
			$("#enddate").hide();
			$("#search").show();
		}
	});

	$("#search-btn").on("click", () => {
		const selected = $("option:selected").val();
		let searchquery = "";
		const search = $("#search").val();
	
		if (selected === "title" && search) {
			searchquery = `title=${encodeBase64(search)}`;
		} else if (selected === "username" && search) {
			searchquery = `username=${encodeBase64(search)}`;
		} else {
			let startdate = new Date($("#startdate").val());
			let enddate = new Date($("#enddate").val());
			startdate.setDate(startdate.getDate() - 1);
			enddate.setDate(enddate.getDate() + 1);
			searchquery = `startdate=${startdate.getTime()}&enddate=${enddate.getTime()}`;
		}

		location.href = `/admin/board?${searchquery}`;
	});
});
