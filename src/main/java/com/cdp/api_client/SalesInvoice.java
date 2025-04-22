package com.cdp.api_client;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesInvoice {
    private String name;
    private String customer;
    private String company;
    private String company_address;
    private String currency;
    private List<Item> items;
    private List<Tax> taxes;
    private double grand_total;
    private String against_income_account;
    private String gst_category;
    private String place_of_supply;
    private int update_stock = 1;
    private String custom_shipment_code;
    private String set_warehouse;
    private String set_target_warehouse;
    private int docstatus = 1;
    private String custom_order_code;
    private Integer is_return = 0;
    private String return_against;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String item_code;
        private double qty;
        private Double rate;
        private Double amount;
        private String income_account;
        private String gst_treatment;
        private String item_tax_template;
        private Map<String, Double> item_tax_rate;
        private int idx;
        private String sales_order;
        private Double taxable_value;
        private Double net_amount;
        private int docstatus = 1;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tax {
        private String doctype;
        private String charge_type;
        private String account_head;
        private String description;
        private Double tax_amount;
        private Double rate;
        private int reference_row;
        private int included_in_print_rate;

    }


}

