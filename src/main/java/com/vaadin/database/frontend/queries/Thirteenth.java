package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;


    @Route(value = "query_13", layout = QueryView.class)
    @PageTitle("Query №13")
    public class Thirteenth extends VerticalLayout {
        private CallerService callerService;
        private final Grid<Object[]> grid;


        public Thirteenth(CallerService callerService ) {
            this.callerService = callerService;
            this.grid = new Grid<>();

            configureGrid();
            add(new H3("Получить перечень должников"), getToolBar(), grid);
            setSizeFull();
        }

        private void configureGrid() {
            grid.addColumn(objects -> objects[0]).setHeader("Id атс");
            grid.addColumn(objects -> objects[1]).setHeader("Id звонящего");
            grid.addColumn(objects -> objects[2]).setHeader("заблокировн");
            grid.addColumn(objects -> objects[3]).setHeader("Фамилия");
            grid.addColumn(objects -> objects[4]).setHeader("Имя");
            grid.addColumn(objects -> objects[5]).setHeader("Отчество");
            grid.addColumn(objects -> objects[6]).setHeader("Абонентский долг");
            grid.addColumn(objects -> objects[7]).setHeader("Долг за межгород");
            grid.addColumn(objects -> objects[8]).setHeader("Срок абонентского долга");
            grid.addColumn(objects -> objects[9]).setHeader("Срок долга за межгород");


            grid.getColumns().forEach(col -> col.setAutoWidth(true));
            setSizeFull();

            grid.setItems(Collections.emptyList());
        }

        private HorizontalLayout getToolBar() {
            Button exec1 = new Button("найти должников для уведомления об отключения", click -> findDebtorsToNotify());
            Button exec2 = new Button("найти должников для отключения", click -> findDebtorsToDisable());
            HorizontalLayout toolbar = new HorizontalLayout(exec1, exec2);
            return toolbar;
        }

        private void findDebtorsToNotify() {
            List<Object[]> data = null;

            data = callerService.findDebtorsToNotify();
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }

        private void findDebtorsToDisable() {
            List<Object[]> data = null;

            data = callerService.findDebtorsToDisable();
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
    }

