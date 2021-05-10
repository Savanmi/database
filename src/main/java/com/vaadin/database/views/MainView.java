package com.vaadin.database.views;

import com.vaadin.database.data.entity.Callers;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    Grid<Callers> grid = new Grid<>(Callers.class);

    public MainView() {

        addClassName("list-view");
        setSizeFull();

        add(grid);










//        // Create and add header text
//        add(new H3("Accessing in-memory database using JdbcTemplate"));
//
//        // create Grid component
//        final Grid<Movie> movies = new Grid<>(Movie.class);
//
//        // fetch all movies from our Service
//        movies.setItems(movieService.getMovies());
//
//        // Use these auto-generated columns
//        movies.setColumns("title", "releaseYear");
//
//        // Add 'Director' column
//        movies.addColumn(movie -> movie.getDirector().getName()).setHeader("Director");
//
//        // Add link to iMDB column; the TemplateRenderer allows us to use a HTML link.
//        movies.addColumn(
//                TemplateRenderer.<Movie>of("<a href='[[item.imbdLink]]' target='_blank'>Click to IMBD site</a>").withProperty("imbdLink", Movie::getImbdLink))
//                .setHeader("IMBD Link");
//
//        // set one column to specific width
//        movies.getColumnByKey("releaseYear").setWidth("55px");
//
//        // Add Grid to view
//        add(movies);
    }

}
