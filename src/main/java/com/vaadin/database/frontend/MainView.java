package com.vaadin.database.frontend;

import com.vaadin.database.frontend.views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;


@Route("")
public class MainView extends AppLayout {

    private MenuBar menuBar = new MenuBar();

    public MainView() {


        MenuItem fees = menuBar.addItem("Fees");

        fees.getSubMenu().addItem(new RouterLink("Fees", Subscription_feeView.class));

        createDrawer();










    }
    private void createDrawer() {
        RouterLink phones = new RouterLink("Phones", PhonesView.class);
        phones.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink callers = new RouterLink("Callers", CallersView.class);
        callers.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink address = new RouterLink("Address", AddressView.class);
        address.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink balances = new RouterLink("Balances", BalancesView.class);
        balances.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink clients = new RouterLink("Clients", ClientsView.class);
        clients.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink connect_prices = new RouterLink("Connect prices", Connection_pricesView.class);
        connect_prices.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink connect_requests = new RouterLink("Connect requests", Connection_requestsView.class);
        connect_requests.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink ldc = new RouterLink("LDC", Long_distance_callsView.class);
        ldc.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink ldc_prices = new RouterLink("LDC prices", Long_distance_call_pricesView.class);
        ldc_prices.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink installing_possibilities = new RouterLink("Installing possibilities", Installing_possibilitiesView.class);
        installing_possibilities.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink phone_numbers = new RouterLink("Phone numbers", Phone_numbersView.class);
        phone_numbers.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink phone_types = new RouterLink("Phone types", Phone_typesView.class);
        phone_types.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink public_phones = new RouterLink("Public phones", Public_phonesView.class);
        public_phones.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink fee = new RouterLink("Subscription fees", Subscription_feeView.class);
        fee.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink tex = new RouterLink("Telephone exchanges", Telephone_exchangesView.class);
        tex.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(address, balances, callers, clients,connect_prices, connect_requests, installing_possibilities,ldc, ldc_prices,phone_numbers, phone_types,public_phones, fee, tex ));
    }

}
