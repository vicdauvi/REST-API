package com.example.hellorestapi.hellorestapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
//@RequestMapping("/hello")
public class ApiController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


	//private static final Logger logger = LogManager.getLogger(Controller.class);

	@RequestMapping(value = "/hello")
	public String firstPage() {
		//logger.info("Log4j info working");
		return ("YAS BABAY");
	}
	
	@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

	/**
	 * Get all users list.
	 *
	 * @return the list
	 */
	@RequestMapping(value = "/getvilles", method = RequestMethod.GET)
	public List<Ville> getAllVilles() {
		
		List<Ville> listeVilles = new ArrayList<Ville>() ;
		
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from ville_france"); 
			
			System.out.println(rs.toString());
			
			while(rs.next()) { 
				listeVilles.add(new Ville(rs.getString(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7))) ;
			}
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return listeVilles ;
	}
	
	/*@CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/getvilles")
    public Ville ville() {
        System.out.println("==== in greeting ====");
        return new Ville(counter.incrementAndGet());
    }
	
	 @GetMapping("/greeting-javaconfig")
	    public Greeting greetingWithJavaconfig(@RequestParam(required=false, defaultValue="World") String name) {
	        System.out.println("==== in greeting ====");
	        return new Greeting(counter.incrementAndGet(), String.format(template, name));
	    }*/
	
	@RequestMapping(value = "/getville", method = RequestMethod.GET)
	public Ville getVille(@Valid @RequestParam(value = "ville")String nomVille) {
		Ville ville = null ;
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery("SELECT * FROM ville_france WHERE Nom_Commune = '"+nomVille+"'");
			while(rs.next()) { 
				ville = new Ville(rs.getString(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)) ;
			}
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ville;
		
	}

	/**
	 * crée une ville dans la bdd
	 *
	 * @param user the user
	 * @return the user
	 */
	@RequestMapping(value = "/postville", method = RequestMethod.POST)
	public void createVille(@Valid @RequestBody Ville ville) {
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("INSERT INTO ville_france VALUES("+ville.toString()+")");
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@RequestMapping(value = "/deleteville", method = RequestMethod.DELETE)
	public void deleteVille(@RequestParam(value="id")String id) {
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("DELETE FROM ville_france WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	/**
	 * Requetes update permettant de modifier un paramètre à partir de l'id de chaque ville
	 */
	@RequestMapping(value = "/updatenomcommune", method = RequestMethod.PUT)
	public void updateNomVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "nom") String nom) {
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Nom_commune='"+nom+"'"
					+"WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	@RequestMapping(value = "/updatecodepostal", method = RequestMethod.PUT)
	public void updateCpVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "cp") String cp) {
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Code_postal='"+cp+"'"
					+"WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	@RequestMapping(value = "/updatelibelle", method = RequestMethod.PUT)
	public void updateLibVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "lib") String lib) { 
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Libelle_acheminement='"+lib+"'"
					+"WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	@RequestMapping(value = "/updateligne", method = RequestMethod.PUT)
	public void updateLigneVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "ligne") String ligne) {
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Ligne_5='"+ligne+"'"
					+"WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	@RequestMapping(value = "/updatelatitude", method = RequestMethod.PUT)
	public void updateLatVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "lat") String lat) {
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Latitude='"+lat+"'"
					+"WHERE Code_commune_INSEE='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}
	
	@RequestMapping(value = "/updatelongitude", method = RequestMethod.PUT)
	public void updateLonVille(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "lon") String lon) { 
			
		try {
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/ville_france","root","");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE ville_france "
					+"SET Nom_commune='"+lon+"'"
					+"WHERE Longitude='"+id+"'");
			con.close(); 
			System.out.println("Requete passee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Requete pas passée");
		} 
	}

}
