

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>JSP Page</title>
        
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

        <link rel="stylesheet" href="/static/css/login.css">
    </head>
    <body>
        
        <%--<form action='/login' method='post'>
        <input name='login' placeholder='Login'>
        <input name='password' placeholder='Password' type='password'>
        <input type='submit' value='Login'>
        </form>
        --%>
        
        <div class="container">
	<header>
		<h1>
			<a href="#">
				<img src="http://tfgms.com/sandbox/dailyui/logo-1.png" alt="Authentic Collection">
			</a>
		</h1>
	</header>
	<h1 class="text-center">Registration</h1>
        <%if (request.getAttribute("errorId") != null) {%>
              <h5 style='color:red'> <%out.println(request.getAttribute("errorId"));%> </h5>
              <%}%>
	<form action='/login' method='post'>
		<label class="col-one-half">
			<span class="label-text">First Name</span>
			<input type="text" name="firstName">
		</label>
		<label class="col-one-half">
			<span class="label-text">Last Name</span>
			<input type="text" name="lastName">
		</label>
		<label>
			<span class="label-text">Login</span>
			<input type="text" name="login">
		</label>
		<label class="password">
			<span class="label-text">Password</span>
			<button class="toggle-visibility" title="toggle password visibility" tabindex="-1">
				<span class="glyphicon glyphicon-eye-close"></span>
			</button>
			<input type="password" name="password">
		</label>
		<label class="checkbox">
			<input type="checkbox" name="newsletter">
			<span>Sign me up for the weekly newsletter.</span>
		</label>
		<div class="text-center">
			<button class="submit" name="register" >Sign Me Up</button>
		</div>
	</form>

    </body>
</html>


