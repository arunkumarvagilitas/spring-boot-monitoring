package com.cdp.dto.pack_order;

import com.cdp.api_client.SalesInvoice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryNote {
    private String customer;
    private String sales_order;
    private int docstatus;
    private List<Item> items;
    private List<SalesInvoice.Tax> taxes;

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
        private int docstatus;
        private String against_sales_order;
        private String so_detail;
    }
}