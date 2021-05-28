package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_3", layout = QueryView.class)
@PageTitle("Query №3")


public class Third extends VerticalLayout {

    private CallerService callerService;
    private final Grid<Object[]> grid;


    public Third(CallerService callerService) {
        this.callerService = callerService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить перечень должников"),getToolBar(), grid);
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

    private VerticalLayout getToolBar() {
        IntegerField min_subscription_debt_age = new IntegerField();
        min_subscription_debt_age.setPlaceholder("Срок абонентского долга от");
        min_subscription_debt_age.setWidthFull();

        IntegerField min_long_distance_debt_age = new IntegerField();
        min_long_distance_debt_age.setPlaceholder("Срок долга за межгород от");
        min_long_distance_debt_age.setWidthFull();

        IntegerField min_long_distance_debt = new IntegerField();
        min_long_distance_debt.setPlaceholder("Сумма долга за межгород от");
        min_long_distance_debt.setWidthFull();

        IntegerField min_subscription_debt = new IntegerField();
        min_subscription_debt.setPlaceholder("Сумма абонентского долга от");
        min_subscription_debt.setWidthFull();

        Button exec = new Button("найти", click -> findPublicPhonesList(min_long_distance_debt.getValue(),min_long_distance_debt_age.getValue(), min_subscription_debt.getValue(), min_subscription_debt_age.getValue()));

        VerticalLayout toolbar = new VerticalLayout(min_subscription_debt, min_long_distance_debt, min_subscription_debt_age,min_long_distance_debt_age, exec);
        return  toolbar;
    }

    private void findPublicPhonesList(Integer min_long_distance_debt, Integer min_long_distance_debt_age, Integer min_subscription_debt, Integer min_subscription_debt_age) {
        List<Object[]> data = null;

        if(min_long_distance_debt != null ){
            data = callerService.findDebtorsListbyLDCDebt(min_long_distance_debt);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
        else if(min_long_distance_debt_age != null ){
            data = callerService.findDebtorsListbyLDCDebtAge(min_long_distance_debt_age);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        } else if (min_subscription_debt != null ){
            data = callerService.findDebtorsListbySubscriptionDebt(min_subscription_debt);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }else if(min_subscription_debt_age != null ){
            data = callerService.findDebtorsListbySubscriptionDebtAge(min_subscription_debt_age);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        } else{
            grid.setItems(Collections.emptyList());
        }
    }


}
