<#import "/spring.ftl" as spring />
<!DOCTYPE html>

<html lang="en">
<head>
	<#assign home><@spring.url relativeUrl="/"/></#assign>
	<#assign jquery><@spring.url relativeUrl="/js/jquery-2.1.3.min.js"/></#assign>
	<#assign bootstrap><@spring.url relativeUrl="/bootstrap-3.3.2-dist/js/bootstrap.min.js"/></#assign>
    <script type="application/javascript" src="${jquery}" ></script>
    <script type="application/javascript" src="${bootstrap}" ></script>
	<link rel="stylesheet" type="text/css" href="/css/dashboard.css">
</head>

<body>
	<hr>
	home: ${home}
	<br>
	Date: ${time?date}
	<br>
	Time: ${time?time}
	<br>
	Message: ${message}
</body>

</html>
