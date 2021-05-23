package org.sid.Cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.Cinema.dao.CategorieRepository;
import org.sid.Cinema.dao.CinemaRepository;
import org.sid.Cinema.dao.FilmRepository;
import org.sid.Cinema.dao.PlaceRepository;
import org.sid.Cinema.dao.ProjectionRepository;
import org.sid.Cinema.dao.SalleRepository;
import org.sid.Cinema.dao.SeanceRepository;
import org.sid.Cinema.dao.TicketRepository;
import org.sid.Cinema.dao.VilleRepository;
import org.sid.Cinema.entities.Categorie;
import org.sid.Cinema.entities.Cinema;
import org.sid.Cinema.entities.Film;
import org.sid.Cinema.entities.Place;
import org.sid.Cinema.entities.Projection;
import org.sid.Cinema.entities.Salle;
import org.sid.Cinema.entities.Seance;
import org.sid.Cinema.entities.Ticket;
import org.sid.Cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
	@Autowired
private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public void initVillees() {
		Stream.of("casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
			
		Ville ville=new Ville();
		ville.setName(nameVille);
		villeRepository.save(ville);
		});
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Megarama","Imax","Founoun","chahrazad","Daouliz")
			.forEach(nameCinema->{
				Cinema cinema=new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle salle=new Salle();
				salle.setName("Salle "+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(15+(int)(Math.random()*20));
				salleRepository.save(salle);
			}
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
				Place place=new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
		placeRepository.save(place);		
		}
});
		
	}

	@Override
	public void initSeances() {
		DateFormat dateformat=new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","19:00","21:00").forEach(s->{
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateformat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("histoire","Actions","Fiction","drama").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
		
	}

	@Override
	public void initfilms() {
		double[]duree=new double[] {1,1.5,2,2.5,3};
		List<Categorie> categories=categorieRepository.findAll();
		Stream.of("Game of thrones","Seigneur des anneaux","Spider man","12 homes en colaire","Forset Gump","le parin").forEach(f->{
			Film film=new Film();
			film.setTitre(f);
			film.setDuree(duree[new Random().nextInt(duree.length)]);
			film.setPhoto(f.replaceAll(" ",""));
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
		
	}

	@Override
	public void initProjections() {
		double[] prices =new double[] {30,50,60,70,90,100};
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							Projection projection=new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setPrix(prices[new Random().nextInt(prices.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionRepository.save(projection);
							
						});
					});
				});
			});
		});
		
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);	
				ticketRepository.save(ticket);
			});
				
			
		});
		
	}

}
