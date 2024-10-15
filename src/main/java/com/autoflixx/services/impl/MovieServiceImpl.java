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
            movie1.setSinopsis("En los años 50, Danny y Sandy viven un romance de verano que parece terminar al regresar a la escuela. Sin embargo, cuando Sandy inesperadamente se une al mismo instituto, ambos deben lidiar con las diferencias entre sus estilos de vida y las presiones sociales de la época.");
            movie1.setPosterImg("PELI-03.png");
            movie1.setBannerImg("BAN-PEL-03.png");
            movie1.setFechaPub(sdf.parse("08-10-2024"));
            movie1.setDisponible(true);
            movie1.setDirector("Randal Kleiser");
            movie1.setGenero("Musical");
            movie1.setDuracion(110);
            movie1.setTrailerUrl("https://www.youtube.com/embed/THd96gHV7Tg?si=ZLQz9oxtLDBN_uTm");

            // Movie 2: Deadpool
            MovieModel movie2 = new MovieModel();
            movie2.setId(2);
            movie2.setNombre("Deadpool");
            movie2.setSinopsis("Wade Wilson, un ex mercenario convertido en antihéroe, adquiere habilidades extraordinarias tras un experimento fallido que le deja desfigurado. Con su humor sarcástico y sed de venganza, Deadpool busca al hombre responsable de su transformación mientras enfrenta desafíos cómicos y violentos.");
            movie2.setPosterImg("PELI-02.png");
            movie2.setBannerImg("BAN-PEL-02.png"); // Optional banner image
            movie2.setFechaPub(sdf.parse("08-10-2024"));
            movie2.setDisponible(true);
            movie2.setDirector("Tim Miller");
            movie2.setGenero("Acción, Comedia");
            movie2.setDuracion(108);
            movie2.setTrailerUrl("https://www.youtube.com/embed/0JnRdfiUMa8?si=fIZnOYZYubhabTcz");
            

            // Movie 3: Home Alone
            MovieModel movie3 = new MovieModel();
            movie3.setId(3);
            movie3.setNombre("Home Alone");
            movie3.setSinopsis("Kevin McCallister, un niño de ocho años, accidentalmente se queda solo en casa cuando su familia se va de vacaciones navideñas. Aprovecha su independencia hasta que dos ladrones intentan robar su casa, y Kevin usa toda su creatividad para defenderse y proteger su hogar.");
            movie3.setPosterImg("PELI-06.png");
            movie3.setBannerImg(null);
            movie3.setFechaPub(sdf.parse("08-10-2024"));
            movie3.setDisponible(false);
            movie3.setDirector("Chris Columbus");
            movie3.setGenero("Comedia, Familia");
            movie3.setDuracion(103);
            movie3.setTrailerUrl("https://www.youtube.com/embed/jEDaVHmw7r4?si=XbyX6wJImMkGiJm0");


            // Movie 4: Oppenheimer
            MovieModel movie4 = new MovieModel();
            movie4.setId(4);
            movie4.setNombre("Oppenheimer");
            movie4.setSinopsis("La película narra la vida de J. Robert Oppenheimer, el físico teórico que dirigió el desarrollo de la bomba atómica durante el Proyecto Manhattan. A través de su historia, se exploran las implicaciones éticas y personales de crear una de las armas más destructivas de la historia.");
            movie4.setPosterImg("PELI-05.png");
            movie4.setBannerImg("BAN-PEL-05.png");
            movie4.setFechaPub(sdf.parse("08-10-2024"));
            movie4.setDisponible(true);
            movie4.setDirector("Christopher Nolan");
            movie4.setGenero("Drama, Historia");
            movie4.setDuracion(180);
            movie4.setTrailerUrl("https://www.youtube.com/embed/gMPEbJQun68?si=shFoeC6x8VVygnu0");

            // Movie 5: La La Land
            MovieModel movie5 = new MovieModel();
            movie5.setId(5);
            movie5.setNombre("La La Land");
            movie5.setSinopsis("En Los Ángeles, la aspirante a actriz Mia y el pianista de jazz Sebastian se conocen y comienzan una relación. Mientras luchan por cumplir sus sueños artísticos, su amor es puesto a prueba por las decisiones y sacrificios necesarios para alcanzar el éxito.");
            movie5.setPosterImg("PELI-04.png");
            movie5.setBannerImg("BAN-PEL-04.png");
            movie5.setFechaPub(sdf.parse("08-10-2024"));
            movie5.setDisponible(true);
            movie5.setDirector("Damien Chazelle");
            movie5.setGenero("Drama, Romance");
            movie5.setDuracion(128);
            movie5.setTrailerUrl("https://www.youtube.com/embed/0pdqf4P9MB8?si=vc-tinuu7bo01Mr1");


            // Movie 6: Alien
            MovieModel movie6 = new MovieModel();
            movie6.setId(6);
            movie6.setNombre("Alien");
            movie6.setSinopsis(
                    "La tripulación de la nave espacial Nostromo responde a una señal de socorro en un planeta remoto, solo para descubrir una forma de vida alienígena mortal. Atrapados y enfrentándose a un enemigo desconocido, deberán luchar por su supervivencia en un entorno aterrador e implacable.");
            movie6.setPosterImg("PELI-01.png");
            movie6.setBannerImg(null);
            movie6.setFechaPub(sdf.parse("08-10-2024"));
            movie6.setDisponible(false);
            movie6.setDirector("Ridley Scott");
            movie6.setGenero("Horror, Sci-Fi");
            movie6.setDuracion(117);
            movie6.setTrailerUrl("https://www.youtube.com/embed/5nWH2Pd-x-c?si=t8H0VkmYEzhhn46_");

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
        for (MovieModel v : movies) {
            if (v.getId() == idMovie) {
                return v;
            }
        }
        return null;
    }

}
