package com.vemis.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.github.jsonldjava.core.RDFDataset.Literal;
import com.vemis.model.Login;
import com.vemis.model.User;
import com.vemis.utilitaires.Constantes;

@Repository
public class UserDaoImpl implements UserDao {
  
  static final String inputFileName_vemis_users = Constantes.VEMIS_USERS;  
  OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
  OntologieUtilitaire ou = new OntologieUtilitaire();
       
  public void register(User user) {
    /*String sql = "insert into users values(?,?,?,?,?,?,?)";
    jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
    user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });*/
    }
    public boolean validateUser(Login login) {    
    	// read camera ontology
    	String log = login.getUsername();
		String mp = login.getPassword();
		String queryString = 	"PREFIX user: <http://www.vemis.sn/6c/ontologies/2018/11/users#> "+
				"PREFIX rdfs: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
				"SELECT * { "+								
				"?user rdfs:type user:Utilisateur . "+	
				"?user user:aPourLogin ?login . "+	
				"?user user:aPourMotDePass ?pw . "+		
				"?login rdfs:type user:Login . "+																					
				"?pw rdfs:type user:MotDePass . "+
				"FILTER (?login = user:"+log+") . "+
				"FILTER (?pw = user:"+mp+") . "+
				"}";
		ResultSet results;
		// read camera ontology
		ou.readOntology(inputFileName_vemis_users, model );
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, model);		
		try {
			 results = qexec.execSelect(); 
			 ResultSetFormatter.out(System.out, results, query);
			 if (results.getRowNumber()!=0) {
					return true;
				}
				else {
					return false;
				}
		 } catch(Exception e) {
			 return false;
		 }
		 finally{
			 qexec.close();
		 }		
    }
  }
  /*class UserMapper implements RowMapper<User> {
  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    User user = new User();
    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setFirstname(rs.getString("firstname"));
    user.setLastname(rs.getString("lastname"));
    user.setEmail(rs.getString("email"));
    user.setAddress(rs.getString("address"));
    user.setPhone(rs.getInt("phone"));
    return user;
  }
}*/
