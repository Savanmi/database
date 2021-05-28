package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.*;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

    @Route(value = "query_12", layout = QueryView.class)
    @PageTitle("Query №12")
    public class Twelfth extends VerticalLayout {
        private Long_distance_callsService long_distance_callsService;
        private final Grid<Object[]> grid;


        public Twelfth(Long_distance_callsService long_distance_callsService) {
            this.long_distance_callsService = long_distance_callsService;
            this.grid = new Grid<>();

            configureGrid();
            add(new H3("Получить перечень телефонов, с которых за некоторый период времени было произведено менее определенного числа звонков"), getToolBar(), grid);
            setSizeFull();
        }

        private void configureGrid() {
            grid.addColumn(objects -> objects[0]).setHeader("Id телефона");
            grid.addColumn(objects -> objects[1]).setHeader("Количество звонков");


            grid.getColumns().forEach(col -> col.setAutoWidth(true));
            setSizeFull();

            grid.setItems(Collections.emptyList());
        }

        private VerticalLayout getToolBar() {
            IntegerField count = new IntegerField("Максимальное количество звонков");
            DateTimePicker first = new DateTimePicker("Дата от...");
            DateTimePicker second = new DateTimePicker("Дата до...");

            Button exec = new Button("найти", click -> findPhonesList(count.getValue(), first.getValue(), second.getValue()));
            VerticalLayout toolbar = new VerticalLayout(count, first, second, exec);
            return toolbar;
        }

        private void findPhonesList(Integer p1, LocalDateTime p2, LocalDateTime p3) {
            List<Object[]> data = null;

            data = long_distance_callsService.FindPhonesList(p1, p2,p3);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
    }
