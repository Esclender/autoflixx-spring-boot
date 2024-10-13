package com.autoflixx.services.impl;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflixx.models.MovieModel;
import com.autoflixx.services.IMovieService;


@Service
public class MovieServiceImpl implements IMovieService {

    private List<MovieModel> movies = null;

    public MovieServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        movies = new LinkedList<>();

        try {
            // Movie 1: Grease
            MovieModel movie1 = new MovieModel();
            movie1.setId(1);
            movie1.setNombre("Grease");
            movie1.setSinopsis("A musical about teens in love in the 1950s.");
            movie1.setPosterImg("PELI-03.png");
            movie1.setBannerImg("BAN-PEL-03.png"); 
            movie1.setFechaPub(sdf.parse("08-10-2024"));
            movie1.setDisponible(true);
            movie1.setDirector("Randal Kleiser");
            movie1.setGenero("Musical");
            movie1.setDuracion(110); 
            movie1.setTrailerUrl("https://www.youtube.com/watch?v=THd96gHV7Tg");

            // Movie 2: Deadpool
            MovieModel movie2 = new MovieModel();
            movie2.setId(2);
            movie2.setNombre("Deadpool");
            movie2.setSinopsis("A wisecracking mercenary gets experimented on and becomes immortal.");
            movie2.setPosterImg("PELI-02.png");
            movie2.setBannerImg("BAN-PEL-02.png"); // Optional banner image
            movie2.setFechaPub(sdf.parse("08-10-2024"));
            movie2.setDisponible(true);
            movie2.setDirector("Tim Miller");
            movie2.setGenero("Acción, Comedia");
            movie2.setDuracion(108);
            movie2.setTrailerUrl("https://www.youtube.com/watch?v=0JnRdfiUMa8");

            // Movie 3: Home Alone
            MovieModel movie3 = new MovieModel();
            movie3.setId(3);
            movie3.setNombre("Home Alone");
            movie3.setSinopsis("An eight-year-old troublemaker must protect his house from burglars.");
            movie3.setPosterImg("PELI-06.png");
            movie3.setBannerImg(null);
            movie3.setFechaPub(sdf.parse("08-10-2024"));
            movie3.setDisponible(false);
            movie3.setDirector("Chris Columbus");
            movie3.setGenero("Comedia, Familia");
            movie3.setDuracion(103);
            movie3.setTrailerUrl("https://www.youtube.com/watch?v=jEDaVHmw7r4");

            // Movie 4: Oppenheimer
            MovieModel movie4 = new MovieModel();
            movie4.setId(4);
            movie4.setNombre("Oppenheimer");
            movie4.setSinopsis("The story of J. Robert Oppenheimer and the development of the atomic bomb.");
            movie4.setPosterImg("PELI-05.png");
            movie4.setBannerImg("BAN-PEL-05.png");
            movie4.setFechaPub(sdf.parse("08-10-2024"));
            movie4.setDisponible(true);
            movie4.setDirector("Christopher Nolan");
            movie4.setGenero("Drama, Historia");
            movie4.setDuracion(180);
            movie4.setTrailerUrl("https://www.youtube.com/watch?v=gMPEbJQun68");

            // Movie 5: La La Land
            MovieModel movie5 = new MovieModel();
            movie5.setId(5);
            movie5.setNombre("La La Land");
            movie5.setSinopsis("A jazz musician and an aspiring actress fall in love in Los Angeles.");
            movie5.setPosterImg("PELI-04.png");
            movie5.setBannerImg("BAN-PEL-04.png");
            movie5.setFechaPub(sdf.parse("08-10-2024"));
            movie5.setDisponible(true);
            movie5.setDirector("Damien Chazelle");
            movie5.setGenero("Drama, Romance");
            movie5.setDuracion(128);
            movie1.setTrailerUrl("https://www.youtube.com/watch?v=0pdqf4P9MB8");

            // Movie 6: Alien
            MovieModel movie6 = new MovieModel();
            movie6.setId(6);
            movie6.setNombre("Alien");
            movie6.setSinopsis(
                    "After a space merchant vessel perceives an unknown transmission as a distress call, one of the crew is attacked by a mysterious life form.");
            movie6.setPosterImg("PELI-01.png");
            movie6.setBannerImg(null);
            movie6.setFechaPub(sdf.parse("08-10-2024"));
            movie6.setDisponible(false);
            movie6.setDirector("Ridley Scott");
            movie6.setGenero("Horror, Sci-Fi");
            movie6.setDuracion(117);
            movie1.setTrailerUrl("https://www.youtube.com/watch?v=5nWH2Pd-x-c");

            movies.add(movie1);
            movies.add(movie2);
            movies.add(movie3);
            movies.add(movie4);
            movies.add(movie5);
            movies.add(movie6);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MovieModel> getAllMovies() {
        return movies;
    }

    @Override
	public MovieModel getMovieById(Integer idMovie) {
		for (MovieModel v: movies) {
			if(v.getId() == idMovie) {
				return v;
			}
		}
		return null;
	}

}
