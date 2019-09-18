<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'un mod�le de simulation</title>   

</head>
<body>


<div class="space-1"></div>
<div class="section section-tabs">
		<div class="title">
		     <h2>Ajout de mod�le de simulation</h2>
		</div>
		<hr>
      <div class="container">
        <!--                nav tabs	             -->
        <div id="nav-tabs">
        <form:form id="loginForm" modelAttribute="identiteModele" action="ajouterIdentiteModeleProcess" method="post">          
          <div class="row">          	
            <div class="col-md-12">              
              <!-- Tabs pour identification du mod�le -->
              <div class="card card-nav-tabs">
                <div class="card-header card-header-info">
                  <!-- colors: "header-primary", "header-info", "header-success", "header-warning", "header-danger" -->
                  <div class="nav-tabs-navigation">
                    <div class="nav-tabs-wrapper">
                      <ul class="nav nav-tabs" data-tabs="tabs">
                        <li class="nav-item">                          
                            <i class="material-icons">face</i> IDENTIFICATION DU MODELE                          
                        </li>                        
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card-body ">
                  <div class="tab-content">
                    <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            Nom du mod�le:
                            <form:input path="nomModele" name="nomModele" id="nomModele" class="form-control"/>                                
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Titre de publication:
                            <form:input path="titrePublication" name="titrePublication" id="titrePublication" class="form-control"/>                                
                     	</div>
                    </div>     
                    <div class="col-md-3">
                        <div class="form-group">
                            Journal de publication:
                            <form:input path="journalPublication" name="journalPublication" id="journalPublication" class="form-control"/>                                
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Maladie cibl�e:
                            <form:select path="MaladieCiblee" name="MaladieCiblee" id="MaladieCiblee" class="form-control">
                            	<form:option value="-" label="--- Selectionnez ---"/>
                				<form:options items="${maladiesCibles}" />
                            </form:select>                              
                     	</div>
                    </div>                    
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            Courte description du mod�le:
                            <form:input path="courteDescription" name="courteDescription" id="courteDescription" class="form-control"/>                                
                     	</div>
                    </div>                                       
                </div>
                  </div>
                  <form:button id="identiteModele" name="identiteModele" class="btn btn-sm btn-info">
							Enregistrer
				  </form:button>
                </div>
              </div>
              <!-- End Tabs pour identification du mod�le -->
            </div>                     
          </div>
          </form:form>   
          <form:form id="loginForm" modelAttribute="supprimerAuteurModele" action="supprimerAuteur" method="post">
          <div class="row">          
            <div class="col-md-12">              
              <!-- Tabs pour auteurs du mod�le -->
              <div class="card card-nav-tabs">
                <div class="card-header card-header-info">
                  <!-- colors: "header-primary", "header-info", "header-success", "header-warning", "header-danger" -->
                  <div class="nav-tabs-navigation">
                    <div class="nav-tabs-wrapper">
                      <ul class="nav nav-tabs" data-tabs="tabs">
                        <li class="nav-item">                          
                            <i class="material-icons">account_circle</i> AUTEURS DU MODELE                          
                        </li>                        
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card-body ">
                  <div class="tab-content">
                    <table class="table table-hover table-striped table-bordered table-condensed">

                        <tr>
                            <th>
                                Nom
                            </th>
                            <th>
                                Email
                            </th>
                            <th>
                                Laboratoire
                            </th>
                            <th>
                                Institution
                            </th>
                            <th>
                            	Pays
                            </th>
                            <th>
                            </th>
                        </tr>                        
						<c:forEach var="order" items="${auteursModele}"> 
					      <tr>
					         <td> ${order.prenom} ${order.nom} </td>
					         <td> ${order.email} </td>
					         <td> ${order.laboratoire} </td>
					         <td> ${order.institution} </td>
					         <td> ${order.pays} </td>
					         <form:hidden path="numeroElement" name="numeroElement" id="numeroElement" class="form-control"/>
					         <td>
					         	<button type="submit" title="Supprimer cet auteur" class="btn btn-sm btn-danger" onclick="document.getElementById('numeroElement').value = '${order.numero}';" >
                                        <i class="glyphicon glyphicon-remove"></i> Supprimer
                                </button>                                					         
                             </td>
					      </tr>
    					</c:forEach>                        
                    </table>
                  </div>
                  <button type="button" class="btn btn-sm btn-info" data-toggle="modal" id="btnShowModalAddNewAuteur" >
            		Ajouter un auteur
        		  </button>               		  		 
                </div>
              </div>
              <!-- End Tabs pour auteurs du mod�le -->
            </div>                        
          </div>
          </form:form>
          <form:form id="loginForm" modelAttribute="proprietesModele" action="ajouterProprietesModeleProcess" method="post">
          <div class="row">
            <div class="col-md-12">              
              <!-- Tabs pour les propri�t�s du mod�le -->
              <div class="card card-nav-tabs">
                <div class="card-header card-header-info">
                  <!-- colors: "header-primary", "header-info", "header-success", "header-warning", "header-danger" -->
                  <div class="nav-tabs-navigation">
                    <div class="nav-tabs-wrapper">
                      <ul class="nav nav-tabs" data-tabs="tabs">
                        <li class="nav-item">                          
                            <i class="material-icons">schedule</i> PROPRIETES, LANGAGE ET PLATEFORME DE SIMULATION DU MODELE                          
                        </li>                        
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card-body ">
                  <div class="tab-content">
                    <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            Fonction objective du mod�le:
                            <form:select path="fonctionObjective" name="fonctionObjective" id="fonctionObjective" class="form-control">
                            	<form:option value="0" label="--- Selectionnez ---" />
                            	<c:forEach var="itemGroup1" items="${fonctionsObjectives}" varStatus="itemGroupIndex">
							       <optgroup label="${itemGroup1.key}">
							           <form:options items="${itemGroup1.value}"/>        
							       </optgroup>
    							</c:forEach>                            	
            				</form:select>                                  
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Nature Fonction objective:
                            <form:select path="natureFonctionObjective" name="natureFonctionObjective" id="natureFonctionObjective" class="form-control">
                            	<form:option value="-" label="--- Selectionnez ---"/>
                				<form:options items="${naturesFonctionsObjectives}" />
                            </form:select>                                
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Cible Fonction objective:
                            <form:select path="elementDeFonctionObjective" name="elementDeFonctionObjective" id="elementDeFonctionObjective" class="form-control">
                            	<form:option value="0" label="--- Selectionnez ---" />
                            	<c:forEach var="itemGroup2" items="${elementsDeModele}" varStatus="itemGroupIndex">
							       <optgroup label="${itemGroup2.key}">
							           <form:options items="${itemGroup2.value}"/>        
							       </optgroup>
    							</c:forEach>                            	
            				</form:select>                                
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Niveau / Echelle du mod�le:
                            <form:select path="dimensionModele" name="dimensionModele" id="dimensionModele" class="form-control">
                            	<form:option value="0" label="--- Selectionnez ---" />
                            	<c:forEach var="itemGroup3" items="${dimensions}" varStatus="itemGroupIndex">
							       <optgroup label="${itemGroup3.key}">
							           <form:options items="${itemGroup3.value}"/>        
							       </optgroup>
    							</c:forEach>                            	
            				</form:select>                               
                     	</div>
                    </div>                                            
                </div>
                <br/><br/>
                <div class="row">
                	<div class="col-md-3">
                        <div class="form-group">
                            Outil / Type de mod�lisation:
                            <form:select path="outilModelisation" name="outilModelisation" id="outilModelisation" class="form-control">
                            	<form:option value="0" label="--- Selectionnez ---" />
                            	<c:forEach var="itemGroup4" items="${typeDeModele}" varStatus="itemGroupIndex">
							       <optgroup label="${itemGroup4.key}">
							           <form:options items="${itemGroup4.value}"/>        
							       </optgroup>
    							</c:forEach>                            	
            				</form:select>                                 
                     	</div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            Langage d'impl�mentation:
                            <form:select path="langageImplementation" name="langageImplementation" id="langageImplementation" class="form-control">
                            	<form:option value="-" label="--- Selectionnez ---"/>
                				<form:options items="${langagesDImplementation}" />
                            </form:select>                                 
                     	</div>
                    </div>   
                    <div class="col-md-3">
                        <div class="form-group">
                            Plateforme de simulation:
                            <form:select path="plateformeSimulation" name="plateformeSimulation" id="plateformeSimulation" class="form-control">
                            	<form:option value="-" label="--- Selectionnez ---"/>
                				<form:options items="${plateformesDeSimulation}" />
                            </form:select>                                 
                     	</div>
                    </div>                                    
                </div>
                  </div>
                  <form:button id="proprietesModele" name="proprietesModele" class="btn btn-sm btn-info">
							Enregistrer
				  </form:button>
                </div>
              </div>
              <!-- End Tabs pour identification du mod�le -->
            </div>            
          </div>
          </form:form>
          <div class="row">
            <div class="col-md-12">              
              <!-- Tabs pour les �l�ments du mod�le -->
              <div class="card card-nav-tabs">
                <div class="card-header card-header-info">
                  <!-- colors: "header-primary", "header-info", "header-success", "header-warning", "header-danger" -->
                  <div class="nav-tabs-navigation">
                    <div class="nav-tabs-wrapper">
                      <ul class="nav nav-tabs" data-tabs="tabs">
                        <li class="nav-item">                          
                            <i class="material-icons">list</i> ELEMENTS DU MODELE                          
                        </li>                        
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card-body ">
                  <div class="tab-content">
                    <div class="row">
							    <div class="col-sm-6">							    
									  PARAMETRES
									  <hr>
									  <table class="table table-hover table-striped table-bordered table-condensed">

					                        <tr>
					                            <th>
					                                Nature
					                            </th>
					                            <th>
					                                Nom
					                            </th>
					                            <th>
					                                Type
					                            </th>					                            
					                        </tr>
					                        <tr>
					                            <td>
					                                Papa Alioune CISSE
					                            </td>
					                            <td>
					                                papaaliounecisse@yahoo.fr
					                            </td>
					                            <td>
					                                LANI (Laboratoire d'Analyse Num�rique et d'Informatique)
					                            </td>					                            
					                        </tr>					                                             
					                    </table>
					                    <button type="button" id="btnShowModalAddNewParametre" class="btn btn-sm btn-info" >
							            	<span class="glyphicon glyphicon-plus"></span>Ajouter un param�tre
							        	</button>								 							     
							    </div>							    
							    <div class="col-sm-6">	
							    <form:form id="loginForm" modelAttribute="supprimerHypotheseModele" action="supprimerHypothese" method="post">						      					       
							        	HYPOTHESES	
							        	<hr>						         
							        	<table class="table table-hover table-striped table-bordered table-condensed">

					                        <tr>
					                            <th>
					                                Nature
					                            </th>
					                            <th>
					                                Porte sur
					                            </th>
					                            <th>
					                                de l'�l�ment
					                            </th>
					                            <th></th>					                            
					                        </tr>
					                        <c:forEach var="order" items="${hypothesesModele}"> 
											      <tr>
											         <td> ${order.natureHypothese} </td>
											         <td> ${order.quantificationHypothese} </td>
											         <td> ${order.elementDHypothese} </td>
											         <form:hidden path="numeroElement" name="numeroElement" id="numeroElement" class="form-control"/>
											         <td>
											         	<button type="submit" title="Supprimer cette hypoth�se" class="btn btn-sm btn-danger" onclick="document.getElementById('numeroElement').value = '${order.numero}';" >
						                                        <i class="glyphicon glyphicon-remove"></i> Supprimer
						                                </button>                                					         
						                             </td>
											      </tr>
    										</c:forEach> 				                                             
					                    </table>							         
							        <button type="button" class="btn btn-sm btn-info" data-toggle="modal" data-target="#modalAddNewHypothese" >
            							<i class="material-icons">library_books</i> Ajouter une hypoth�se
        		  					</button>         		  					            
							        </form:form>		        							      
							    </div>								    						   
						</div>    
						<div class="space-70"></div>
						<div class="row">
							    <div class="col-sm-6">							      						        
							        	ACTEURS
										<hr>							         
							        	<table class="table table-hover table-striped table-bordered table-condensed">

					                        <tr>
					                            <th>
					                                Nature
					                            </th>
					                            <th>
					                                Nom
					                            </th>
					                            <th>
					                                Type
					                            </th>					                            
					                        </tr>
					                        <tr>
					                            <td>
					                                Papa Alioune CISSE
					                            </td>
					                            <td>
					                                papaaliounecisse@yahoo.fr
					                            </td>
					                            <td>
					                                LANI (Laboratoire d'Analyse Num�rique et d'Informatique)
					                            </td>					                            
					                        </tr>					                                             
					                    </table>							         	
							        <button type="button" id="btnShowModalAddNewActeur" class="btn btn-sm btn-info" >
							            	<span class="glyphicon glyphicon-plus"></span>Ajouter un acteur
							        	</button>					        							      
							    </div>
							    <div class="col-sm-6">							      						        
							        	OBJETS
							        	<hr>							        							         
							        	<table class="table table-hover table-striped table-bordered table-condensed">

					                        <tr>
					                            <th>
					                                Nature
					                            </th>
					                            <th>
					                                Nom
					                            </th>
					                            <th>
					                                Type
					                            </th>					                            
					                        </tr>
					                        <tr>
					                            <td>
					                                Papa Alioune CISSE
					                            </td>
					                            <td>
					                                papaaliounecisse@yahoo.fr
					                            </td>
					                            <td>
					                                LANI (Laboratoire d'Analyse Num�rique et d'Informatique)
					                            </td>					                            
					                        </tr>					                                             
					                    </table>							         
							        <button type="button" id="btnShowModalAddNewObjet" class="btn btn-sm btn-info" >
							            	<span class="glyphicon glyphicon-plus"></span>Ajouter un objet
							        </button>				        							      
							    </div>							    
						</div> 
						<div class="space-70"></div>
						<div class="row">
							    <div class="col-sm-6">							      					        
							        	ACTIVITES
							        	<hr>							        							         
							        	<table class="table table-hover table-striped table-bordered table-condensed">
					                        <tr>
					                            <th>
					                                Nature
					                            </th>
					                            <th>
					                                Nom
					                            </th>
					                            <th>
					                                Type
					                            </th>					                            
					                        </tr>
					                        <tr>
					                            <td>
					                                Papa Alioune CISSE
					                            </td>
					                            <td>
					                                papaaliounecisse@yahoo.fr
					                            </td>
					                            <td>
					                                LANI (Laboratoire d'Analyse Num�rique et d'Informatique)
					                            </td>					                            
					                        </tr>					                                             
					                    </table>							         
							        <button type="button" id="btnShowModalAddNewActivite" class="btn btn-sm btn-info" >
							            	<span class="glyphicon glyphicon-plus"></span>Ajouter une activit�
							        	</button>				        							      
							    </div>							   
						</div>
                  </div>
                </div>
              </div>
              <!-- End Tabs pour identification du mod�le -->
            </div>            
          </div>
        </div>
      </div>
    </div>
    <!-- 	            end nav tabs	             -->  
</body>
</html>

<!-- Classic Modal -->
      
  <!--  End Modal -->