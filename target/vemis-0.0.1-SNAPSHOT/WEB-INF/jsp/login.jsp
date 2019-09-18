<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<div class="page-header header-filter" style="background-image: url('${pageContext.request.contextPath}/ressources/template/img/bg7.jpg'); background-size: cover; background-position: top center;">
    <div class="container">
      <div class="row">
        <div class="col-lg-4 col-md-6 ml-auto mr-auto">
          <div class="card card-login">
            <form:form class="form" id="loginForm" modelAttribute="login" action="loginProcess" method="post">
              <div class="card-header card-header-primary text-center">
                <h4 class="card-title">Login</h4>
                <div class="social-line">
                  <a href="#pablo" class="btn btn-just-icon btn-link">
                    <i class="fa fa-facebook-square"></i>
                  </a>
                  <a href="#pablo" class="btn btn-just-icon btn-link">
                    <i class="fa fa-twitter"></i>
                  </a>
                  <a href="#pablo" class="btn btn-just-icon btn-link">
                    <i class="fa fa-google-plus"></i>
                  </a>
                </div>
              </div>
              <p class="description text-center">Or Be Classical</p>
              <div class="card-body">
                <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="material-icons">face</i>
                    </span>
                  </div>
                  <form:input path="username" name="username" id="username" class="form-control" placeholder="User Name"/>                  
                </div>
                <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="material-icons">lock_outline</i>
                    </span>
                  </div>
                  <form:password path="password" name="password" id="password" class="form-control" placeholder="Password"/>                  
                </div>
              </div>
              <div class="footer text-center">
              	<form:button id="login" name="login" class="btn btn-primary btn-link btn-wd btn-lg">
							Login
				</form:button>                
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>    
  </div>                        
<!-- <div class="limiter">
		<div class="container-login100" style="background-image: url('images/bg-01.jpg');">
			<div class="wrap-login100">
				<form:form class="login100-form validate-form" id="loginForm" modelAttribute="login" action="loginProcess" method="post">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
					</span>

					<span class="login100-form-title p-b-34 p-t-27">
						Log in
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Enter username">						
						<form:input path="username" name="username" id="username" class="input100"/>
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">						
						<form:password path="password" name="password" id="password" class="input100" />
						<span class="focus-input100" data-placeholder="&#xf191;"></span>
					</div>

					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Remember me
						</label>
					</div>

					<div class="container-login100-form-btn">
						<form:button id="login" name="login" class="login100-form-btn">
							Login
						</form:button>
					</div>

					<div class="text-center p-t-90">
						<a class="txt1" href="#">
							Forgot Password?
						</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>	 -->
</body>
</html>