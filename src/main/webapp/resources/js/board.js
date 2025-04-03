const searchParam = new URLSearchParams(window.location.search)

$(() => {
	if(searchParam.get("title")) {
		$("#searchfield").val("title").prop("selected", true)
		$("#search").val(searchParam.get("title"))
	} else if(searchParam.get("username")) {
		$("#searchfield").val("username").prop("selected", true)
		$("#search").val(searchParam.get("username"))
	} else if(searchParam.get("startdate")) {
		let startdate = new Date(Number(searchParam.get("startdate")))
		let enddate = new Date(Number(searchParam.get("enddate")))
		
		startdate.setDate(startdate.getDate() + 1)
		enddate.setDate(enddate.getDate() - 1)
		$("#searchfield").val("date").prop("selected", true)
		$("#startdate").val(startdate.toISOString().substring(0, 10)).show()
		$("#enddate").val(enddate.toISOString().substring(0, 10)).show()
		$("#search").hide()
	}
	$("#searchfield").change(() => { 
		const selected = $("option:selected").val()
		if (selected == "date") {
			const $startdate = $("#startdate")
			const $enddate = $("#enddate")
			if($startdate.val() == "") $startdate.val(new Date().toISOString().substring(0, 10))
			if($enddate.val() == "") $enddate.val(new Date().toISOString().substring(0, 10))
			$("#startdate").show()
			$("#enddate").show()
			$("#search").hide()
		} else {
			$("#startdate").hide()
			$("#enddate").hide()
			$("#search").show()
		}
    })
	$("#search-btn").on("click", () => {
		const selected = $("option:selected").val()
		let searchquery = ""
		const search = $("#search").val()
		if(selected == "title") searchquery = `title=${search}`
		else if(selected == "username") searchquery = `username=${search}`
		else {
			let startdate = new Date($("#startdate").val())
			let enddate = new Date($("#enddate").val())
			startdate.setDate(startdate.getDate() - 1)
			enddate.setDate(enddate.getDate() + 1)
			searchquery = `startdate=${startdate.getTime()}&enddate=${enddate.getTime()}` 
		}
		location.href=`/board/main?board_id=${searchParam.get("board_id")}&${searchquery}`
	})
})