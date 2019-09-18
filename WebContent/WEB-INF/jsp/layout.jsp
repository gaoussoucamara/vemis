<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>               

<!--     From template     -->
<!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- Material Kit CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ressources/template/css/material-kit.css?v=2.0.5" />
<!--     From template/     -->

              
</head>  
<body >  

<nav class="navbar navbar-color-on-scroll navbar-transparent fixed-top navbar-expand-lg bg-info" color-on-scroll="100">
    <div class="container">
      <div class="navbar-translate">
        <a class="navbar-brand" href="#">
          <small>
            <img src="${pageContext.request.contextPath}/ressources/img/logo_ceamitic.png" alt="" width="135" height="35"/>            
          </small>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="sr-only">Toggle navigation</span>
          <span class="navbar-toggler-icon"></span>
          <span class="navbar-toggler-icon"></span>
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
        	<li class="dropdown nav-item">
	            <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
	              <i class="material-icons">settings</i> PARAMETRAGE
	            </a>
	            <div class="dropdown-menu dropdown-with-icons">
	              <a href="ajouterModele" class="dropdown-item">
	                <i class="material-icons">layers</i> MODELE DE SIMULATION
	              </a>              
	            </div>
          </li>
          <li class="nav-item">
            <a href="login" class="nav-link">
              <i class="material-icons">account_circle</i> Se Connecter
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="page-header header-filter" data-parallax="true" style="background-image: url('${pageContext.request.contextPath}/ressources/template/img/bg3.jpg')">
    <div class="container">
      <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
          <div class="brand text-center">
            <h1>VEMIS</h1>
            <h3 class="title text-center">VEILLE EPIDEMIOLOGIQUE DES MALADIES INFECTIEUSES AU SENEGAL</h3>
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- Les différentes pages utilisant le layout -->
<div class="main main-raised">
	<div class="section section-basic">
	    <div class="container">	  
	      <tiles:insertAttribute name="body" />
	    </div>	
  </div>
</div>
<!-- Classic Modal -->
<div class="modal fade" id="modalAddNewHypothese" tabindex="-1" role="dialog">
  	<form:form class="form" id="formAddNewHypothese" modelAttribute="uneHypotheseModele" action="AjouterHypotheseProcess" method="post">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-info">
          <h5 class="modal-title">AJOUT D'UNE HYPOTHESE</h5>
          <form:button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="material-icons">clear</i>
          </form:button>
        </div>
        <div class="modal-body">
          <div class="tab-content">
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        NATURE:
                        <form:select path="natureHypothese" name="natureHypothese" id="natureHypothese" class="form-control">
                           	<form:option value="-" label="--- Selectionnez ---"/>
               				<form:options items="${naturesDHypotheses}" />
                        </form:select>
                 	</div>
	            </div>
	            <div class="col-md-6">
                    <div class="form-group">
                        PORTE SUR:
                        <form:select path="quantificationHypothese" name="quantificationHypothese" id="quantificationHypothese" class="form-control">
                           	<form:option value="-" label="--- Selectionnez ---"/>
               				<form:options items="${elementsDeQuantification}" />
                        </form:select>
                 	</div>
	            </div>
          	</div>
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        DE L'ELEMENT:
                        <form:select path="elementDHypothese" name="elementDHypothese" id="elementDHypothese" class="form-control">
                           	<form:option value="0" label="--- Selectionnez ---" />
                           	<c:forEach var="itemGroup4" items="${elementsDeModele}" varStatus="itemGroupIndex">
						       <optgroup label="${itemGroup4.key}">
						           <form:options items="${itemGroup4.value}"/>        
						       </optgroup>
   							</c:forEach>                            	
            			</form:select> 
                 	</div>
	            </div>	            
          	</div>          	         
          </div>
        </div>
        <div class="modal-footer">
          <form:button id="ajouterHypothese" name="ajouterHypothese" class="btn btn-sm btn-info" type="submit" value="submit">
				Ajouter
		  </form:button> 
		  <form:button id="ajouterHypothese" name="ajouterHypothese" class="btn btn-sm btn-danger" data-dismiss="modal">
				Annuler
		  </form:button>  
        </div>
      </div>
    </div>
    </form:form>
  </div>  

<div class="modal fade" id="modalAddNewParametre" tabindex="-1" role="dialog">
  	<form:form class="form" id="formAddNewParametre" modelAttribute="unParametreModele" action="AjouterParametreProcess" method="post">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-info">
          <h5 class="modal-title">AJOUT D'UN PARAMETRE</h5>
          <form:button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="material-icons">clear</i>
          </form:button>
        </div>
        <div class="modal-body">
          <div class="tab-content">
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        TYPE:
                        <form:select path="typeParametre" name="typeParametre" id="typeParametre" class="form-control">
                           	<form:option value="-" label="--- Selectionnez ---"/>
               				<form:options items="${typesDeParametres}" />
                        </form:select>
                 	</div>
	            </div>
	            <div class="col-md-6">
                    <div class="form-group">
                        PORTE SUR:
                        <form:select path="quantificationParametre" name="quantificationParametre" id="quantificationParametre" class="form-control">
                           	<form:option value="-" label="--- Selectionnez ---"/>
               				<form:options items="${elementsDeQuantification}" />
                        </form:select>
                 	</div>
	            </div>
          	</div>
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        DE L'ELEMENT:
                        <form:select path="elementParametre" name="elementParametre" id="elementParametre" class="form-control">
                           	<form:option value="0" label="--- Selectionnez ---" />
                           	<c:forEach var="itemGroup5" items="${elementsDeModele}" varStatus="itemGroupIndex">
						       <optgroup label="${itemGroup5.key}">
						           <form:options items="${itemGroup5.value}"/>        
						       </optgroup>
   							</c:forEach>                            	
            			</form:select> 
                 	</div>
	            </div>	            
          	</div>          	         
          </div>
        </div>
        <div class="modal-footer">
          <form:button id="ajouterParametre" name="ajouterParametre" class="btn btn-sm btn-info" type="submit" value="submit">
				Ajouter
		  </form:button> 
		  <form:button id="ajouterParametre" name="ajouterParametre" class="btn btn-sm btn-danger" data-dismiss="modal">
				Annuler
		  </form:button>  
        </div>
      </div>
    </div>
    </form:form>
  </div>
  
  <div class="modal fade" id="modalAddNewEntite" tabindex="-1" role="dialog">
  	<form:form class="form" id="formAddNewEntite" modelAttribute="uneEntiteModele" action="AjouterEntiteProcess" method="post">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-info">
          <h5 class="modal-title">AJOUT D'UNE ENTITE</h5>
          <form:button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="material-icons">clear</i>
          </form:button>
        </div>
        <div class="modal-body">
          <div class="tab-content">
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        CHOISIR L'ENTITE:
                        <form:select path="nomEntite" name="nomEntite" id="nomEntite" class="form-control">
                           	<form:option value="0" label="--- Selectionnez ---" />
                           	<c:forEach var="itemGroup6" items="${elementsDeModele}" varStatus="itemGroupIndex">
						       <optgroup label="${itemGroup6.key}">
						           <form:options items="${itemGroup6.value}"/>        
						       </optgroup>
   							</c:forEach>                            	
            			</form:select> 
                 	</div>
	            </div>	            
          	</div>          	         
          </div>
        </div>
        <div class="modal-footer">
          <form:button id="ajouterEntite" name="ajouterEntite" class="btn btn-sm btn-info" type="submit" value="submit">
				Ajouter
		  </form:button> 
		  <form:button id="ajouterEntite" name="ajouterEntite" class="btn btn-sm btn-danger" data-dismiss="modal">
				Annuler
		  </form:button>  
        </div>
      </div>
    </div>
    </form:form>
  </div>  

  
  <div class="modal fade" id="modalAddNewAuteur" tabindex="-1" role="dialog">
  	<form:form class="form" id="formAddNewAuteur" modelAttribute="unAuteurModele" action="AjouterAuteurProcess" method="post">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-info">
          <h5 class="modal-title">AJOUT D'UN AUTEUR</h5>
          <form:button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="material-icons">clear</i>
          </form:button>
        </div>
        <div class="modal-body">
          <div class="tab-content">
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        NOM:
                        <form:input path="nom" name="nom" id="nom" class="form-control"/>                                
                 	</div>
	            </div>
	            <div class="col-md-6">
                    <div class="form-group">
                        PRENOM:
                        <form:input path="prenom" name="prenom" id="prenom" class="form-control"/>                                
                 	</div>
	            </div>
          	</div>
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        EMAIL:
                        <form:input path="email" name="email" id="email" class="form-control"/>                                
                 	</div>
	            </div>
	            <div class="col-md-6">
                    <div class="form-group">
                        LABORATOIRE:
                        <form:input path="laboratoire" name="laboratoire" id="laboratoire" class="form-control"/>                                
                 	</div>
	            </div>
          	</div>
          	<div class="row">
	       		<div class="col-md-6">
                    <div class="form-group">
                        INSTITUTION:
                        <form:input path="institution" name="institution" id="institution" class="form-control"/>                                
                 	</div>
	            </div>
	            <div class="col-md-6">
                    <div class="form-group">
                        PAYS:
                        <form:input path="pays" name="pays" id="pays" class="form-control"/>                                
                 	</div>
	            </div>
          	</div>          	
          </div>
        </div>
        <div class="modal-footer">
          <form:button id="ajouterAuteur" name="ajouterAuteur" class="btn btn-sm btn-info" type="submit" value="submit">
				Ajouter
		  </form:button> 
		  <form:button id="ajouterAuteur" name="ajouterAuteur" class="btn btn-sm btn-danger" data-dismiss="modal">
				Annuler
		  </form:button>  
        </div>
      </div>
    </div>
    </form:form>
  </div> 
  
    
  <!--  End Modal -->
<!-- Le footer du layout -->
<footer class="footer footer-default">
    <div class="container">
      <nav class="float-left">
        <ul>
          <li>
            <a href="https://www.creative-tim.com/">
              Creative Tim
            </a>
          </li>
        </ul>
      </nav>
      <div class="copyright float-right">
        &copy;
        <script>
          document.write(new Date().getFullYear())
        </script>, made with <i class="material-icons">favorite</i> by
        <a href="https://www.creative-tim.com/" target="blank">Creative Tim</a> for a better web.
      </div>
    </div>
  </footer>
  <!--   Core JS Files   -->
  <script src="${pageContext.request.contextPath}/ressources/template/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/ressources/template/js/core/popper.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/ressources/template/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/ressources/template/js/plugins/moment.min.js"></script>
  <!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
  <script src="${pageContext.request.contextPath}/ressources/template/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="${pageContext.request.contextPath}/ressources/template/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
  <script src="${pageContext.request.contextPath}/ressources/template/js/material-kit.js?v=2.0.5" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/ressources/script/monScript.js" type="text/javascript"></script>
  <script>
    $(document).ready(function() {
      //init DateTimePickers
      materialKit.initFormExtendedDatetimepickers();

      // Sliders Init
      materialKit.initSliders();            
    });


    function scrollToDownload() {
      if ($('.section-download').length != 0) {
        $("html, body").animate({
          scrollTop: $('.section-download').offset().top
        }, 1000);
      }
    }


    $(document).ready(function() {

      $('#facebook').sharrre({
        share: {
          facebook: true
        },
        enableHover: false,
        enableTracking: false,
        enableCounter: false,
        click: function(api, options) {
          api.simulateClick();
          api.openPopup('facebook');
        },
        template: '<i class="fab fa-facebook-f"></i> Facebook',
        url: 'https://demos.creative-tim.com/material-kit/index.html'
      });

      $('#googlePlus').sharrre({
        share: {
          googlePlus: true
        },
        enableCounter: false,
        enableHover: false,
        enableTracking: true,
        click: function(api, options) {
          api.simulateClick();
          api.openPopup('googlePlus');
        },
        template: '<i class="fab fa-google-plus"></i> Google',
        url: 'https://demos.creative-tim.com/material-kit/index.html'
      });

      $('#twitter').sharrre({
        share: {
          twitter: true
        },
        enableHover: false,
        enableTracking: false,
        enableCounter: false,
        buttons: {
          twitter: {
            via: 'CreativeTim'
          }
        },
        click: function(api, options) {
          api.simulateClick();
          api.openPopup('twitter');
        },
        template: '<i class="fab fa-twitter"></i> Twitter',
        url: 'https://demos.creative-tim.com/material-kit/index.html'
      });

    });
  </script>
</body>  
</html>  