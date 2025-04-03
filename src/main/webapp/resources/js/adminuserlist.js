const searchParam = new URLSearchParams(window.location.search)
$(() => {
	if(searchParam.get("id")) {
		$("#searchfield").val("id").prop("selected", true)
		$("#search").val(searchParam.get("id"))
	} else if(searchParam.get("name")) {
		$("#searchfield").val("name").prop("selected", true)
		$("#search").val(searchParam.get("name"))
	} else if(searchParam.get("username")) {
		$("#searchfield").val("username").prop("selected", true)
		$("#search").val(searchParam.get("username"))
	} else if(searchParam.get("role")) {
		$("#searchfield").val("role").prop("selected", true)
		$("#search").hide()
		$("#role").val(searchParam.get("role")).prop("selected", true)
		$("#role").show()
	}
	$("#searchfield").on("change", () => {
		const selected = $("#searchfield option:selected").val()
		if(selected == "role") {
			$("#search").hide();
			$("#role").show();
		} else {
			$("#search").show();
			$("#role").hide();
		}
	})
	$("#search-btn").on("click", () => {
		const selected = $("#searchfield option:selected").val()
		const search = $("#search").val();
		let searchquery = "";
		if(selected == "id") searchquery = `id=${search}`
		else if(selected == "name") searchquery = `name=${search}`
		else if(selected == "username") searchquery = `username=${search}`
		else searchquery = `role=${$("#role option:selected").val()}` 
		location.href=`/admin/user?${searchquery}`
	})
	
})