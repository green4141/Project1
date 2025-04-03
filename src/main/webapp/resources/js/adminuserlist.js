$(() => {
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