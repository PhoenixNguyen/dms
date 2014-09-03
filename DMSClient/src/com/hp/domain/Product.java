/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 *
 * @author HP
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Comparable<Product> {
	
    private Integer serial;
    
    private String productID;
    private String barcode;
    private String productName;
    private String brand;
    private String origin;
    private String packingSpecifications;
    private String quantification;
    private Float vatTax;
    private Float importPrices;
    private Float exportPrices;
    private String provider;
    private String description;
    private String productImage;

	private int total;
    private int discount;
    private int promotionalProductAmounts;
	private String note;
	
    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackingSpecifications() {
        return packingSpecifications;
    }

    public void setPackingSpecifications(String packingSpecifications) {
        this.packingSpecifications = packingSpecifications;
    }

    public String getQuantification() {
        return quantification;
    }

    public void setQuantification(String quantification) {
        this.quantification = quantification;
    }

    public Float getVatTax() {
        return vatTax;
    }

    public void setVatTax(Float vatTax) {
        this.vatTax = vatTax;
    }

    public Float getImportPrices() {
        return importPrices;
    }

    public void setImportPrices(Float importPrices) {
        this.importPrices = importPrices;
    }

    public Float getExportPrices() {
        return exportPrices;
    }

    public void setExportPrices(Float exportPrices) {
        this.exportPrices = exportPrices;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPromotionalProductAmounts() {
		return promotionalProductAmounts;
	}

	public void setPromotionalProductAmounts(int promotionalProductAmounts) {
		this.promotionalProductAmounts = promotionalProductAmounts;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


    
    
    public Product(String pBarcode, String pProductID, String pProductName,
			String pBrand, String pOrigin, String pPackingSpecifications,
			String pQuantification, float pVATTax, float pImportPrices,
			float pExportPrices, String pProvider, String pDescription,
			String pProductImage) {
		super();
		this.barcode = pBarcode;
		this.productID = pProductID;
		this.productName = pProductName;
		this.brand = pBrand;
		this.origin = pOrigin;
		this.packingSpecifications = pPackingSpecifications;
		this.quantification = pQuantification;
		this.vatTax = pVATTax;
		this.importPrices = pImportPrices;
		this.exportPrices = pExportPrices;
		this.provider = pProvider;
		this.description = pDescription;
		this.productImage = pProductImage;
	}
    
    public Product(){}
    
    

    public int compareTo(Product a) {
        if ( this.total > a.total )
            return 1;
        else 
            return -1;
//        else {
//             if ( this.timeEnded > a.timeEnded )
//                   return 1;
//             else
//                  return -1;
//         }

     }
	@Override
	public String toString() {
		return productID +" " + productName;
				
	}


    public Product(int pSerial, String pBarcode, String pProductID,
			String pProductName) {
		super();
		this.serial = pSerial;
		this.barcode = pBarcode;
		this.productID = pProductID;
		this.productName = pProductName;
	}

    
   
    
}
