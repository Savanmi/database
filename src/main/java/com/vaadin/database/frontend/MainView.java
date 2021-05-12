package com.vaadin.database.frontend;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends VerticalLayout {

    private MenuBar menuBar = new MenuBar();

    public MainView() {


        MenuItem fees = menuBar.addItem("Fees");

        fees.getSubMenu().addItem(new RouterLink("Fees", Subscription_feeView.class));

        add(menuBar);









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

//
//
//
//
//    }

}
