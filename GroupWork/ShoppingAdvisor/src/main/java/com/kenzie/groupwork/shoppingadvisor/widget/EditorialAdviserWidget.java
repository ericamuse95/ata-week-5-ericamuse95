package com.kenzie.groupwork.shoppingadvisor.widget;

import com.kenzie.groupwork.shoppingadvisor.client.EditorialServiceClient;
import com.kenzie.groupwork.shoppingadvisor.model.ShoppingAdviserProduct;
import com.kenzie.groupwork.shoppingadvisor.model.ShoppingContext;
import com.kenzie.groupwork.shoppingadvisor.resources.AmazonsChoiceProduct;
import com.kenzie.groupwork.shoppingadvisor.resources.EditorialRecommendedProduct;
import com.kenzie.groupwork.shoppingadvisor.resources.SearchCategory;
import com.kenzie.groupwork.shoppingadvisor.resources.TextTable;

import java.util.ArrayList;
import java.util.List;

public class EditorialAdviserWidget extends ShoppingAdviserWidget {
    private EditorialServiceClient editorialServiceClient;

    public EditorialAdviserWidget(EditorialServiceClient editorialServiceClient, ShoppingContext shoppingContext) {
        super("Editorial recommendation", shoppingContext);
        this.editorialServiceClient = editorialServiceClient;
    }


    @Override
    public List<ShoppingAdviserProduct> getAdvisedProducts() {
        List<EditorialRecommendedProduct> editorialRecommendedProduct = new ArrayList<>();
        editorialRecommendedProduct = editorialServiceClient.getEditorialRecommendedProducts(getShoppingContext().getSearchTerm(),
                getShoppingContext().getSearchCategory(),
                getShoppingContext().getMarketplaceId());

        List<ShoppingAdviserProduct> shoppingAdviserProducts = new ArrayList<>();
        for (EditorialRecommendedProduct choiceProduct : editorialRecommendedProduct){
            shoppingAdviserProducts.add(new ShoppingAdviserProduct(choiceProduct.getRecommendation(),
                    choiceProduct.getProduct()));
        }
        return shoppingAdviserProducts;
    }



}
