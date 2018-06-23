//-----------------------------------com.basketbird.backend.importer.entity.OpenProduct.java-----------------------------------

package com.basketbird.backend.importer.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"code",
"url",
"creator",
"created_t",
"created_datetime",
"last_modified_t",
"last_modified_datetime",
"product_name",
"generic_name",
"quantity",
"packaging",
"packaging_tags",
"brands",
"brands_tags",
"categories",
"categories_tags",
"categories_en",
"origins",
"origins_tags",
"manufacturing_places",
"manufacturing_places_tags",
"labels",
"labels_tags",
"labels_en",
"emb_codes",
"emb_codes_tags",
"first_packaging_code_geo",
"cities",
"cities_tags",
"purchase_places",
"stores",
"countries",
"countries_tags",
"countries_en",
"ingredients_text",
"allergens",
"allergens_en",
"traces",
"traces_tags",
"traces_en",
"serving_size",
"no_nutriments",
"additives_n",
"additives",
"additives_tags",
"additives_en",
"ingredients_from_palm_oil_n",
"ingredients_from_palm_oil",
"ingredients_from_palm_oil_tags",
"ingredients_that_may_be_from_palm_oil_n",
"ingredients_that_may_be_from_palm_oil",
"ingredients_that_may_be_from_palm_oil_tags",
"nutrition_grade_uk",
"nutrition_grade_fr",
"pnns_groups_1",
"pnns_groups_2",
"states",
"states_tags",
"states_en",
"main_category",
"main_category_en",
"image_url",
"image_small_url",
"energy_100g",
"energy-from-fat_100g",
"fat_100g",
"saturated-fat_100g",
"-butyric-acid_100g",
"-caproic-acid_100g",
"-caprylic-acid_100g",
"-capric-acid_100g",
"-lauric-acid_100g",
"-myristic-acid_100g",
"-palmitic-acid_100g",
"-stearic-acid_100g",
"-arachidic-acid_100g",
"-behenic-acid_100g",
"-lignoceric-acid_100g",
"-cerotic-acid_100g",
"-montanic-acid_100g",
"-melissic-acid_100g",
"monounsaturated-fat_100g",
"polyunsaturated-fat_100g",
"omega-3-fat_100g",
"-alpha-linolenic-acid_100g",
"-eicosapentaenoic-acid_100g",
"-docosahexaenoic-acid_100g",
"omega-6-fat_100g",
"-linoleic-acid_100g",
"-arachidonic-acid_100g",
"-gamma-linolenic-acid_100g",
"-dihomo-gamma-linolenic-acid_100g",
"omega-9-fat_100g",
"-oleic-acid_100g",
"-elaidic-acid_100g",
"-gondoic-acid_100g",
"-mead-acid_100g",
"-erucic-acid_100g",
"-nervonic-acid_100g",
"trans-fat_100g",
"cholesterol_100g",
"carbohydrates_100g",
"sugars_100g",
"-sucrose_100g",
"-glucose_100g",
"-fructose_100g",
"-lactose_100g",
"-maltose_100g",
"-maltodextrins_100g",
"starch_100g",
"polyols_100g",
"fiber_100g",
"proteins_100g",
"casein_100g",
"serum-proteins_100g",
"nucleotides_100g",
"salt_100g",
"sodium_100g",
"alcohol_100g",
"vitamin-a_100g",
"beta-carotene_100g",
"vitamin-d_100g",
"vitamin-e_100g",
"vitamin-k_100g",
"vitamin-c_100g",
"vitamin-b1_100g",
"vitamin-b2_100g",
"vitamin-pp_100g",
"vitamin-b6_100g",
"vitamin-b9_100g",
"folates_100g",
"vitamin-b12_100g",
"biotin_100g",
"pantothenic-acid_100g",
"silica_100g",
"bicarbonate_100g",
"potassium_100g",
"chloride_100g",
"calcium_100g",
"phosphorus_100g",
"iron_100g",
"magnesium_100g",
"zinc_100g",
"copper_100g",
"manganese_100g",
"fluoride_100g",
"selenium_100g",
"chromium_100g",
"molybdenum_100g",
"iodine_100g",
"caffeine_100g",
"taurine_100g",
"ph_100g",
"fruits-vegetables-nuts_100g",
"fruits-vegetables-nuts-estimate_100g",
"collagen-meat-protein-ratio_100g",
"cocoa_100g",
"chlorophyl_100g",
"carbon-footprint_100g",
"nutrition-score-fr_100g",
"nutrition-score-uk_100g",
"glycemic-index_100g",
"water-hardness_100g"
})
public class OpenProduct extends BaseEntity 
{

@JsonProperty("code")
private String code;
@JsonProperty("url")
private String url;
@JsonProperty("creator")
private String creator;
@JsonProperty("created_t")
private String createdT;
@JsonProperty("created_datetime")
private String createdDatetime;
@JsonProperty("last_modified_t")
private String lastModifiedT;
@JsonProperty("last_modified_datetime")
private String lastModifiedDatetime;
@JsonProperty("product_name")
private String productName;
@JsonProperty("generic_name")
private Object genericName;
@JsonProperty("quantity")
private String quantity;
@JsonProperty("packaging")
private Object packaging;
@JsonProperty("packaging_tags")
private Object packagingTags;
@JsonProperty("brands")
private String brands;
@JsonProperty("brands_tags")
private String brandsTags;
@JsonProperty("categories")
private Object categories;
@JsonProperty("categories_tags")
private Object categoriesTags;
@JsonProperty("categories_en")
private Object categoriesEn;
@JsonProperty("origins")
private Object origins;
@JsonProperty("origins_tags")
private Object originsTags;
@JsonProperty("manufacturing_places")
private Object manufacturingPlaces;
@JsonProperty("manufacturing_places_tags")
private Object manufacturingPlacesTags;
@JsonProperty("labels")
private Object labels;
@JsonProperty("labels_tags")
private Object labelsTags;
@JsonProperty("labels_en")
private Object labelsEn;
@JsonProperty("emb_codes")
private Object embCodes;
@JsonProperty("emb_codes_tags")
private Object embCodesTags;
@JsonProperty("first_packaging_code_geo")
private Object firstPackagingCodeGeo;
@JsonProperty("cities")
private Object cities;
@JsonProperty("cities_tags")
private Object citiesTags;
@JsonProperty("purchase_places")
private Object purchasePlaces;
@JsonProperty("stores")
private Object stores;
@JsonProperty("countries")
private String countries;
@JsonProperty("countries_tags")
private String countriesTags;
@JsonProperty("countries_en")
private String countriesEn;
@JsonProperty("ingredients_text")
private Object ingredientsText;
@JsonProperty("allergens")
private Object allergens;
@JsonProperty("allergens_en")
private Object allergensEn;
@JsonProperty("traces")
private Object traces;
@JsonProperty("traces_tags")
private Object tracesTags;
@JsonProperty("traces_en")
private Object tracesEn;
@JsonProperty("serving_size")
private Object servingSize;
@JsonProperty("no_nutriments")
private Object noNutriments;
@JsonProperty("additives_n")
private Object additivesN;
@JsonProperty("additives")
private Object additives;
@JsonProperty("additives_tags")
private Object additivesTags;
@JsonProperty("additives_en")
private Object additivesEn;
@JsonProperty("ingredients_from_palm_oil_n")
private Object ingredientsFromPalmOilN;
@JsonProperty("ingredients_from_palm_oil")
private Object ingredientsFromPalmOil;
@JsonProperty("ingredients_from_palm_oil_tags")
private Object ingredientsFromPalmOilTags;
@JsonProperty("ingredients_that_may_be_from_palm_oil_n")
private Object ingredientsThatMayBeFromPalmOilN;
@JsonProperty("ingredients_that_may_be_from_palm_oil")
private Object ingredientsThatMayBeFromPalmOil;
@JsonProperty("ingredients_that_may_be_from_palm_oil_tags")
private Object ingredientsThatMayBeFromPalmOilTags;
@JsonProperty("nutrition_grade_uk")
private Object nutritionGradeUk;
@JsonProperty("nutrition_grade_fr")
private Object nutritionGradeFr;
@JsonProperty("pnns_groups_1")
private Object pnnsGroups1;
@JsonProperty("pnns_groups_2")
private Object pnnsGroups2;
@JsonProperty("states")
private String states;
@JsonProperty("states_tags")
private String statesTags;
@JsonProperty("states_en")
private String statesEn;
@JsonProperty("main_category")
private Object mainCategory;
@JsonProperty("main_category_en")
private Object mainCategoryEn;
@JsonProperty("image_url")
private Object imageUrl;
@JsonProperty("image_small_url")
private Object imageSmallUrl;
@JsonProperty("energy_100g")
private Object energy100g;
@JsonProperty("energy-from-fat_100g")
private Object energyFromFat100g;
@JsonProperty("fat_100g")
private Object fat100g;
@JsonProperty("saturated-fat_100g")
private Object saturatedFat100g;
@JsonProperty("-butyric-acid_100g")
private Object butyricAcid100g;
@JsonProperty("-caproic-acid_100g")
private Object caproicAcid100g;
@JsonProperty("-caprylic-acid_100g")
private Object caprylicAcid100g;
@JsonProperty("-capric-acid_100g")
private Object capricAcid100g;
@JsonProperty("-lauric-acid_100g")
private Object lauricAcid100g;
@JsonProperty("-myristic-acid_100g")
private Object myristicAcid100g;
@JsonProperty("-palmitic-acid_100g")
private Object palmiticAcid100g;
@JsonProperty("-stearic-acid_100g")
private Object stearicAcid100g;
@JsonProperty("-arachidic-acid_100g")
private Object arachidicAcid100g;
@JsonProperty("-behenic-acid_100g")
private Object behenicAcid100g;
@JsonProperty("-lignoceric-acid_100g")
private Object lignocericAcid100g;
@JsonProperty("-cerotic-acid_100g")
private Object ceroticAcid100g;
@JsonProperty("-montanic-acid_100g")
private Object montanicAcid100g;
@JsonProperty("-melissic-acid_100g")
private Object melissicAcid100g;
@JsonProperty("monounsaturated-fat_100g")
private Object monounsaturatedFat100g;
@JsonProperty("polyunsaturated-fat_100g")
private Object polyunsaturatedFat100g;
@JsonProperty("omega-3-fat_100g")
private Object omega3Fat100g;
@JsonProperty("-alpha-linolenic-acid_100g")
private Object alphaLinolenicAcid100g;
@JsonProperty("-eicosapentaenoic-acid_100g")
private Object eicosapentaenoicAcid100g;
@JsonProperty("-docosahexaenoic-acid_100g")
private Object docosahexaenoicAcid100g;
@JsonProperty("omega-6-fat_100g")
private Object omega6Fat100g;
@JsonProperty("-linoleic-acid_100g")
private Object linoleicAcid100g;
@JsonProperty("-arachidonic-acid_100g")
private Object arachidonicAcid100g;
@JsonProperty("-gamma-linolenic-acid_100g")
private Object gammaLinolenicAcid100g;
@JsonProperty("-dihomo-gamma-linolenic-acid_100g")
private Object dihomoGammaLinolenicAcid100g;
@JsonProperty("omega-9-fat_100g")
private Object omega9Fat100g;
@JsonProperty("-oleic-acid_100g")
private Object oleicAcid100g;
@JsonProperty("-elaidic-acid_100g")
private Object elaidicAcid100g;
@JsonProperty("-gondoic-acid_100g")
private Object gondoicAcid100g;
@JsonProperty("-mead-acid_100g")
private Object meadAcid100g;
@JsonProperty("-erucic-acid_100g")
private Object erucicAcid100g;
@JsonProperty("-nervonic-acid_100g")
private Object nervonicAcid100g;
@JsonProperty("trans-fat_100g")
private Object transFat100g;
@JsonProperty("cholesterol_100g")
private Object cholesterol100g;
@JsonProperty("carbohydrates_100g")
private Object carbohydrates100g;
@JsonProperty("sugars_100g")
private Object sugars100g;
@JsonProperty("-sucrose_100g")
private Object sucrose100g;
@JsonProperty("-glucose_100g")
private Object glucose100g;
@JsonProperty("-fructose_100g")
private Object fructose100g;
@JsonProperty("-lactose_100g")
private Object lactose100g;
@JsonProperty("-maltose_100g")
private Object maltose100g;
@JsonProperty("-maltodextrins_100g")
private Object maltodextrins100g;
@JsonProperty("starch_100g")
private Object starch100g;
@JsonProperty("polyols_100g")
private Object polyols100g;
@JsonProperty("fiber_100g")
private Object fiber100g;
@JsonProperty("proteins_100g")
private Object proteins100g;
@JsonProperty("casein_100g")
private Object casein100g;
@JsonProperty("serum-proteins_100g")
private Object serumProteins100g;
@JsonProperty("nucleotides_100g")
private Object nucleotides100g;
@JsonProperty("salt_100g")
private Object salt100g;
@JsonProperty("sodium_100g")
private Object sodium100g;
@JsonProperty("alcohol_100g")
private Object alcohol100g;
@JsonProperty("vitamin-a_100g")
private Object vitaminA100g;
@JsonProperty("beta-carotene_100g")
private Object betaCarotene100g;
@JsonProperty("vitamin-d_100g")
private Object vitaminD100g;
@JsonProperty("vitamin-e_100g")
private Object vitaminE100g;
@JsonProperty("vitamin-k_100g")
private Object vitaminK100g;
@JsonProperty("vitamin-c_100g")
private Object vitaminC100g;
@JsonProperty("vitamin-b1_100g")
private Object vitaminB1100g;
@JsonProperty("vitamin-b2_100g")
private Object vitaminB2100g;
@JsonProperty("vitamin-pp_100g")
private Object vitaminPp100g;
@JsonProperty("vitamin-b6_100g")
private Object vitaminB6100g;
@JsonProperty("vitamin-b9_100g")
private Object vitaminB9100g;
@JsonProperty("folates_100g")
private Object folates100g;
@JsonProperty("vitamin-b12_100g")
private Object vitaminB12100g;
@JsonProperty("biotin_100g")
private Object biotin100g;
@JsonProperty("pantothenic-acid_100g")
private Object pantothenicAcid100g;
@JsonProperty("silica_100g")
private Object silica100g;
@JsonProperty("bicarbonate_100g")
private Object bicarbonate100g;
@JsonProperty("potassium_100g")
private Object potassium100g;
@JsonProperty("chloride_100g")
private Object chloride100g;
@JsonProperty("calcium_100g")
private Object calcium100g;
@JsonProperty("phosphorus_100g")
private Object phosphorus100g;
@JsonProperty("iron_100g")
private Object iron100g;
@JsonProperty("magnesium_100g")
private Object magnesium100g;
@JsonProperty("zinc_100g")
private Object zinc100g;
@JsonProperty("copper_100g")
private String copper100g;
@JsonProperty("manganese_100g")
private String manganese100g;
@JsonProperty("fluoride_100g")
private String fluoride100g;
@JsonProperty("selenium_100g")
private String selenium100g;
@JsonProperty("chromium_100g")
private String chromium100g;
@JsonProperty("molybdenum_100g")
private String molybdenum100g;
@JsonProperty("iodine_100g")
private String iodine100g;
@JsonProperty("caffeine_100g")
private String caffeine100g;
@JsonProperty("taurine_100g")
private String taurine100g;
@JsonProperty("ph_100g")
private String ph100g;
@JsonProperty("fruits-vegetables-nuts_100g")
private String fruitsVegetablesNuts100g;
@JsonProperty("fruits-vegetables-nuts-estimate_100g")
private String fruitsVegetablesNutsEstimate100g;
@JsonProperty("collagen-meat-protein-ratio_100g")
private String collagenMeatProteinRatio100g;
@JsonProperty("cocoa_100g")
private String cocoa100g;
@JsonProperty("chlorophyl_100g")
private String chlorophyl100g;
@JsonProperty("carbon-footprint_100g")
private String carbonFootprint100g;
@JsonProperty("nutrition-score-fr_100g")
private String nutritionScoreFr100g;
@JsonProperty("nutrition-score-uk_100g")
private String nutritionScoreUk100g;
@JsonProperty("glycemic-index_100g")
private String glycemicIndex100g;
@JsonProperty("water-hardness_100g")
private String waterHardness100g;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -7039451550634957505L;

/**
* No args constructor for use in serialization
* 
*/
public OpenProduct() {
}

/**
* 
* @param nutritionScoreFr100g
* @param allergensEn
* @param eicosapentaenoicAcid100g
* @param tracesEn
* @param countries
* @param fluoride100g
* @param additivesN
* @param silica100g
* @param packaging
* @param carbonFootprint100g
* @param ph100g
* @param saturatedFat100g
* @param glucose100g
* @param quantity
* @param ingredientsText
* @param categoriesEn
* @param iron100g
* @param tracesTags
* @param alphaLinolenicAcid100g
* @param monounsaturatedFat100g
* @param chromium100g
* @param purchasePlaces
* @param transFat100g
* @param ingredientsThatMayBeFromPalmOilTags
* @param fructose100g
* @param energy100g
* @param omega6Fat100g
* @param collagenMeatProteinRatio100g
* @param alcohol100g
* @param cities
* @param allergens
* @param origins
* @param additivesTags
* @param brandsTags
* @param nutritionGradeFr
* @param vitaminA100g
* @param waterHardness100g
* @param manganese100g
* @param additivesEn
* @param nutritionGradeUk
* @param iodine100g
* @param traces
* @param statesTags
* @param packagingTags
* @param manufacturingPlacesTags
* @param states
* @param dihomoGammaLinolenicAcid100g
* @param arachidonicAcid100g
* @param mainCategoryEn
* @param taurine100g
* @param brands
* @param lignocericAcid100g
* @param manufacturingPlaces
* @param vitaminPp100g
* @param potassium100g
* @param cholesterol100g
* @param behenicAcid100g
* @param phosphorus100g
* @param fat100g
* @param ingredientsFromPalmOil
* @param servingSize
* @param fruitsVegetablesNutsEstimate100g
* @param fiber100g
* @param polyols100g
* @param labelsEn
* @param bicarbonate100g
* @param vitaminB2100g
* @param pnnsGroups1
* @param fruitsVegetablesNuts100g
* @param pnnsGroups2
* @param mainCategory
* @param myristicAcid100g
* @param butyricAcid100g
* @param glycemicIndex100g
* @param vitaminB12100g
* @param originsTags
* @param pantothenicAcid100g
* @param copper100g
* @param vitaminC100g
* @param chloride100g
* @param stores
* @param citiesTags
* @param gondoicAcid100g
* @param vitaminD100g
* @param arachidicAcid100g
* @param imageUrl
* @param cocoa100g
* @param embCodes
* @param capricAcid100g
* @param maltodextrins100g
* @param stearicAcid100g
* @param omega3Fat100g
* @param countriesTags
* @param categoriesTags
* @param serumProteins100g
* @param lauricAcid100g
* @param ingredientsFromPalmOilN
* @param createdT
* @param nervonicAcid100g
* @param embCodesTags
* @param ceroticAcid100g
* @param selenium100g
* @param casein100g
* @param labels
* @param chlorophyl100g
* @param erucicAcid100g
* @param code
* @param betaCarotene100g
* @param countriesEn
* @param genericName
* @param proteins100g
* @param url
* @param gammaLinolenicAcid100g
* @param sugars100g
* @param lastModifiedDatetime
* @param caprylicAcid100g
* @param polyunsaturatedFat100g
* @param magnesium100g
* @param carbohydrates100g
* @param starch100g
* @param omega9Fat100g
* @param lastModifiedT
* @param firstPackagingCodeGeo
* @param zinc100g
* @param nucleotides100g
* @param nutritionScoreUk100g
* @param palmiticAcid100g
* @param vitaminB1100g
* @param labelsTags
* @param folates100g
* @param createdDatetime
* @param ingredientsFromPalmOilTags
* @param meadAcid100g
* @param creator
* @param oleicAcid100g
* @param docosahexaenoicAcid100g
* @param molybdenum100g
* @param statesEn
* @param calcium100g
* @param noNutriments
* @param additives
* @param energyFromFat100g
* @param elaidicAcid100g
* @param sodium100g
* @param sucrose100g
* @param vitaminB9100g
* @param salt100g
* @param lactose100g
* @param maltose100g
* @param biotin100g
* @param linoleicAcid100g
* @param ingredientsThatMayBeFromPalmOilN
* @param vitaminE100g
* @param vitaminB6100g
* @param vitaminK100g
* @param melissicAcid100g
* @param montanicAcid100g
* @param categories
* @param ingredientsThatMayBeFromPalmOil
* @param caffeine100g
* @param productName
* @param imageSmallUrl
* @param caproicAcid100g
*/
public OpenProduct(String code, String url, String creator, String createdT, String createdDatetime, String lastModifiedT, String lastModifiedDatetime, String productName, Object genericName, String quantity, Object packaging, Object packagingTags, String brands, String brandsTags, Object categories, Object categoriesTags, Object categoriesEn, Object origins, Object originsTags, Object manufacturingPlaces, Object manufacturingPlacesTags, Object labels, Object labelsTags, Object labelsEn, Object embCodes, Object embCodesTags, Object firstPackagingCodeGeo, Object cities, Object citiesTags, Object purchasePlaces, Object stores, String countries, String countriesTags, String countriesEn, Object ingredientsText, Object allergens, Object allergensEn, Object traces, Object tracesTags, Object tracesEn, Object servingSize, Object noNutriments, Object additivesN, Object additives, Object additivesTags, Object additivesEn, Object ingredientsFromPalmOilN, Object ingredientsFromPalmOil, Object ingredientsFromPalmOilTags, Object ingredientsThatMayBeFromPalmOilN, Object ingredientsThatMayBeFromPalmOil, Object ingredientsThatMayBeFromPalmOilTags, Object nutritionGradeUk, Object nutritionGradeFr, Object pnnsGroups1, Object pnnsGroups2, String states, String statesTags, String statesEn, Object mainCategory, Object mainCategoryEn, Object imageUrl, Object imageSmallUrl, Object energy100g, Object energyFromFat100g, Object fat100g, Object saturatedFat100g, Object butyricAcid100g, Object caproicAcid100g, Object caprylicAcid100g, Object capricAcid100g, Object lauricAcid100g, Object myristicAcid100g, Object palmiticAcid100g, Object stearicAcid100g, Object arachidicAcid100g, Object behenicAcid100g, Object lignocericAcid100g, Object ceroticAcid100g, Object montanicAcid100g, Object melissicAcid100g, Object monounsaturatedFat100g, Object polyunsaturatedFat100g, Object omega3Fat100g, Object alphaLinolenicAcid100g, Object eicosapentaenoicAcid100g, Object docosahexaenoicAcid100g, Object omega6Fat100g, Object linoleicAcid100g, Object arachidonicAcid100g, Object gammaLinolenicAcid100g, Object dihomoGammaLinolenicAcid100g, Object omega9Fat100g, Object oleicAcid100g, Object elaidicAcid100g, Object gondoicAcid100g, Object meadAcid100g, Object erucicAcid100g, Object nervonicAcid100g, Object transFat100g, Object cholesterol100g, Object carbohydrates100g, Object sugars100g, Object sucrose100g, Object glucose100g, Object fructose100g, Object lactose100g, Object maltose100g, Object maltodextrins100g, Object starch100g, Object polyols100g, Object fiber100g, Object proteins100g, Object casein100g, Object serumProteins100g, Object nucleotides100g, Object salt100g, Object sodium100g, Object alcohol100g, Object vitaminA100g, Object betaCarotene100g, Object vitaminD100g, Object vitaminE100g, Object vitaminK100g, Object vitaminC100g, Object vitaminB1100g, Object vitaminB2100g, Object vitaminPp100g, Object vitaminB6100g, Object vitaminB9100g, Object folates100g, Object vitaminB12100g, Object biotin100g, Object pantothenicAcid100g, Object silica100g, Object bicarbonate100g, Object potassium100g, Object chloride100g, Object calcium100g, Object phosphorus100g, Object iron100g, Object magnesium100g, Object zinc100g, String copper100g, String manganese100g, String fluoride100g, String selenium100g, String chromium100g, String molybdenum100g, String iodine100g, String caffeine100g, String taurine100g, String ph100g, String fruitsVegetablesNuts100g, String fruitsVegetablesNutsEstimate100g, String collagenMeatProteinRatio100g, String cocoa100g, String chlorophyl100g, String carbonFootprint100g, String nutritionScoreFr100g, String nutritionScoreUk100g, String glycemicIndex100g, String waterHardness100g) {
super();
this.code = code;
this.url = url;
this.creator = creator;
this.createdT = createdT;
this.createdDatetime = createdDatetime;
this.lastModifiedT = lastModifiedT;
this.lastModifiedDatetime = lastModifiedDatetime;
this.productName = productName;
this.genericName = genericName;
this.quantity = quantity;
this.packaging = packaging;
this.packagingTags = packagingTags;
this.brands = brands;
this.brandsTags = brandsTags;
this.categories = categories;
this.categoriesTags = categoriesTags;
this.categoriesEn = categoriesEn;
this.origins = origins;
this.originsTags = originsTags;
this.manufacturingPlaces = manufacturingPlaces;
this.manufacturingPlacesTags = manufacturingPlacesTags;
this.labels = labels;
this.labelsTags = labelsTags;
this.labelsEn = labelsEn;
this.embCodes = embCodes;
this.embCodesTags = embCodesTags;
this.firstPackagingCodeGeo = firstPackagingCodeGeo;
this.cities = cities;
this.citiesTags = citiesTags;
this.purchasePlaces = purchasePlaces;
this.stores = stores;
this.countries = countries;
this.countriesTags = countriesTags;
this.countriesEn = countriesEn;
this.ingredientsText = ingredientsText;
this.allergens = allergens;
this.allergensEn = allergensEn;
this.traces = traces;
this.tracesTags = tracesTags;
this.tracesEn = tracesEn;
this.servingSize = servingSize;
this.noNutriments = noNutriments;
this.additivesN = additivesN;
this.additives = additives;
this.additivesTags = additivesTags;
this.additivesEn = additivesEn;
this.ingredientsFromPalmOilN = ingredientsFromPalmOilN;
this.ingredientsFromPalmOil = ingredientsFromPalmOil;
this.ingredientsFromPalmOilTags = ingredientsFromPalmOilTags;
this.ingredientsThatMayBeFromPalmOilN = ingredientsThatMayBeFromPalmOilN;
this.ingredientsThatMayBeFromPalmOil = ingredientsThatMayBeFromPalmOil;
this.ingredientsThatMayBeFromPalmOilTags = ingredientsThatMayBeFromPalmOilTags;
this.nutritionGradeUk = nutritionGradeUk;
this.nutritionGradeFr = nutritionGradeFr;
this.pnnsGroups1 = pnnsGroups1;
this.pnnsGroups2 = pnnsGroups2;
this.states = states;
this.statesTags = statesTags;
this.statesEn = statesEn;
this.mainCategory = mainCategory;
this.mainCategoryEn = mainCategoryEn;
this.imageUrl = imageUrl;
this.imageSmallUrl = imageSmallUrl;
this.energy100g = energy100g;
this.energyFromFat100g = energyFromFat100g;
this.fat100g = fat100g;
this.saturatedFat100g = saturatedFat100g;
this.butyricAcid100g = butyricAcid100g;
this.caproicAcid100g = caproicAcid100g;
this.caprylicAcid100g = caprylicAcid100g;
this.capricAcid100g = capricAcid100g;
this.lauricAcid100g = lauricAcid100g;
this.myristicAcid100g = myristicAcid100g;
this.palmiticAcid100g = palmiticAcid100g;
this.stearicAcid100g = stearicAcid100g;
this.arachidicAcid100g = arachidicAcid100g;
this.behenicAcid100g = behenicAcid100g;
this.lignocericAcid100g = lignocericAcid100g;
this.ceroticAcid100g = ceroticAcid100g;
this.montanicAcid100g = montanicAcid100g;
this.melissicAcid100g = melissicAcid100g;
this.monounsaturatedFat100g = monounsaturatedFat100g;
this.polyunsaturatedFat100g = polyunsaturatedFat100g;
this.omega3Fat100g = omega3Fat100g;
this.alphaLinolenicAcid100g = alphaLinolenicAcid100g;
this.eicosapentaenoicAcid100g = eicosapentaenoicAcid100g;
this.docosahexaenoicAcid100g = docosahexaenoicAcid100g;
this.omega6Fat100g = omega6Fat100g;
this.linoleicAcid100g = linoleicAcid100g;
this.arachidonicAcid100g = arachidonicAcid100g;
this.gammaLinolenicAcid100g = gammaLinolenicAcid100g;
this.dihomoGammaLinolenicAcid100g = dihomoGammaLinolenicAcid100g;
this.omega9Fat100g = omega9Fat100g;
this.oleicAcid100g = oleicAcid100g;
this.elaidicAcid100g = elaidicAcid100g;
this.gondoicAcid100g = gondoicAcid100g;
this.meadAcid100g = meadAcid100g;
this.erucicAcid100g = erucicAcid100g;
this.nervonicAcid100g = nervonicAcid100g;
this.transFat100g = transFat100g;
this.cholesterol100g = cholesterol100g;
this.carbohydrates100g = carbohydrates100g;
this.sugars100g = sugars100g;
this.sucrose100g = sucrose100g;
this.glucose100g = glucose100g;
this.fructose100g = fructose100g;
this.lactose100g = lactose100g;
this.maltose100g = maltose100g;
this.maltodextrins100g = maltodextrins100g;
this.starch100g = starch100g;
this.polyols100g = polyols100g;
this.fiber100g = fiber100g;
this.proteins100g = proteins100g;
this.casein100g = casein100g;
this.serumProteins100g = serumProteins100g;
this.nucleotides100g = nucleotides100g;
this.salt100g = salt100g;
this.sodium100g = sodium100g;
this.alcohol100g = alcohol100g;
this.vitaminA100g = vitaminA100g;
this.betaCarotene100g = betaCarotene100g;
this.vitaminD100g = vitaminD100g;
this.vitaminE100g = vitaminE100g;
this.vitaminK100g = vitaminK100g;
this.vitaminC100g = vitaminC100g;
this.vitaminB1100g = vitaminB1100g;
this.vitaminB2100g = vitaminB2100g;
this.vitaminPp100g = vitaminPp100g;
this.vitaminB6100g = vitaminB6100g;
this.vitaminB9100g = vitaminB9100g;
this.folates100g = folates100g;
this.vitaminB12100g = vitaminB12100g;
this.biotin100g = biotin100g;
this.pantothenicAcid100g = pantothenicAcid100g;
this.silica100g = silica100g;
this.bicarbonate100g = bicarbonate100g;
this.potassium100g = potassium100g;
this.chloride100g = chloride100g;
this.calcium100g = calcium100g;
this.phosphorus100g = phosphorus100g;
this.iron100g = iron100g;
this.magnesium100g = magnesium100g;
this.zinc100g = zinc100g;
this.copper100g = copper100g;
this.manganese100g = manganese100g;
this.fluoride100g = fluoride100g;
this.selenium100g = selenium100g;
this.chromium100g = chromium100g;
this.molybdenum100g = molybdenum100g;
this.iodine100g = iodine100g;
this.caffeine100g = caffeine100g;
this.taurine100g = taurine100g;
this.ph100g = ph100g;
this.fruitsVegetablesNuts100g = fruitsVegetablesNuts100g;
this.fruitsVegetablesNutsEstimate100g = fruitsVegetablesNutsEstimate100g;
this.collagenMeatProteinRatio100g = collagenMeatProteinRatio100g;
this.cocoa100g = cocoa100g;
this.chlorophyl100g = chlorophyl100g;
this.carbonFootprint100g = carbonFootprint100g;
this.nutritionScoreFr100g = nutritionScoreFr100g;
this.nutritionScoreUk100g = nutritionScoreUk100g;
this.glycemicIndex100g = glycemicIndex100g;
this.waterHardness100g = waterHardness100g;
}

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

public OpenProduct withCode(String code) {
this.code = code;
return this;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

public OpenProduct withUrl(String url) {
this.url = url;
return this;
}

@JsonProperty("creator")
public String getCreator() {
return creator;
}

@JsonProperty("creator")
public void setCreator(String creator) {
this.creator = creator;
}

public OpenProduct withCreator(String creator) {
this.creator = creator;
return this;
}

@JsonProperty("created_t")
public String getCreatedT() {
return createdT;
}

@JsonProperty("created_t")
public void setCreatedT(String createdT) {
this.createdT = createdT;
}

public OpenProduct withCreatedT(String createdT) {
this.createdT = createdT;
return this;
}

@JsonProperty("created_datetime")
public String getCreatedDatetime() {
return createdDatetime;
}

@JsonProperty("created_datetime")
public void setCreatedDatetime(String createdDatetime) {
this.createdDatetime = createdDatetime;
}

public OpenProduct withCreatedDatetime(String createdDatetime) {
this.createdDatetime = createdDatetime;
return this;
}

@JsonProperty("last_modified_t")
public String getLastModifiedT() {
return lastModifiedT;
}

@JsonProperty("last_modified_t")
public void setLastModifiedT(String lastModifiedT) {
this.lastModifiedT = lastModifiedT;
}

public OpenProduct withLastModifiedT(String lastModifiedT) {
this.lastModifiedT = lastModifiedT;
return this;
}

@JsonProperty("last_modified_datetime")
public String getLastModifiedDatetime() {
return lastModifiedDatetime;
}

@JsonProperty("last_modified_datetime")
public void setLastModifiedDatetime(String lastModifiedDatetime) {
this.lastModifiedDatetime = lastModifiedDatetime;
}

public OpenProduct withLastModifiedDatetime(String lastModifiedDatetime) {
this.lastModifiedDatetime = lastModifiedDatetime;
return this;
}

@JsonProperty("product_name")
public String getProductName() {
return productName;
}

@JsonProperty("product_name")
public void setProductName(String productName) {
this.productName = productName;
}

public OpenProduct withProductName(String productName) {
this.productName = productName;
return this;
}

@JsonProperty("generic_name")
public Object getGenericName() {
return genericName;
}

@JsonProperty("generic_name")
public void setGenericName(Object genericName) {
this.genericName = genericName;
}

public OpenProduct withGenericName(Object genericName) {
this.genericName = genericName;
return this;
}

@JsonProperty("quantity")
public String getQuantity() {
return quantity;
}

@JsonProperty("quantity")
public void setQuantity(String quantity) {
this.quantity = quantity;
}

public OpenProduct withQuantity(String quantity) {
this.quantity = quantity;
return this;
}

@JsonProperty("packaging")
public Object getPackaging() {
return packaging;
}

@JsonProperty("packaging")
public void setPackaging(Object packaging) {
this.packaging = packaging;
}

public OpenProduct withPackaging(Object packaging) {
this.packaging = packaging;
return this;
}

@JsonProperty("packaging_tags")
public Object getPackagingTags() {
return packagingTags;
}

@JsonProperty("packaging_tags")
public void setPackagingTags(Object packagingTags) {
this.packagingTags = packagingTags;
}

public OpenProduct withPackagingTags(Object packagingTags) {
this.packagingTags = packagingTags;
return this;
}

@JsonProperty("brands")
public String getBrands() {
return brands;
}

@JsonProperty("brands")
public void setBrands(String brands) {
this.brands = brands;
}

public OpenProduct withBrands(String brands) {
this.brands = brands;
return this;
}

@JsonProperty("brands_tags")
public String getBrandsTags() {
return brandsTags;
}

@JsonProperty("brands_tags")
public void setBrandsTags(String brandsTags) {
this.brandsTags = brandsTags;
}

public OpenProduct withBrandsTags(String brandsTags) {
this.brandsTags = brandsTags;
return this;
}

@JsonProperty("categories")
public Object getCategories() {
return categories;
}

@JsonProperty("categories")
public void setCategories(Object categories) {
this.categories = categories;
}

public OpenProduct withCategories(Object categories) {
this.categories = categories;
return this;
}

@JsonProperty("categories_tags")
public Object getCategoriesTags() {
return categoriesTags;
}

@JsonProperty("categories_tags")
public void setCategoriesTags(Object categoriesTags) {
this.categoriesTags = categoriesTags;
}

public OpenProduct withCategoriesTags(Object categoriesTags) {
this.categoriesTags = categoriesTags;
return this;
}

@JsonProperty("categories_en")
public Object getCategoriesEn() {
return categoriesEn;
}

@JsonProperty("categories_en")
public void setCategoriesEn(Object categoriesEn) {
this.categoriesEn = categoriesEn;
}

public OpenProduct withCategoriesEn(Object categoriesEn) {
this.categoriesEn = categoriesEn;
return this;
}

@JsonProperty("origins")
public Object getOrigins() {
return origins;
}

@JsonProperty("origins")
public void setOrigins(Object origins) {
this.origins = origins;
}

public OpenProduct withOrigins(Object origins) {
this.origins = origins;
return this;
}

@JsonProperty("origins_tags")
public Object getOriginsTags() {
return originsTags;
}

@JsonProperty("origins_tags")
public void setOriginsTags(Object originsTags) {
this.originsTags = originsTags;
}

public OpenProduct withOriginsTags(Object originsTags) {
this.originsTags = originsTags;
return this;
}

@JsonProperty("manufacturing_places")
public Object getManufacturingPlaces() {
return manufacturingPlaces;
}

@JsonProperty("manufacturing_places")
public void setManufacturingPlaces(Object manufacturingPlaces) {
this.manufacturingPlaces = manufacturingPlaces;
}

public OpenProduct withManufacturingPlaces(Object manufacturingPlaces) {
this.manufacturingPlaces = manufacturingPlaces;
return this;
}

@JsonProperty("manufacturing_places_tags")
public Object getManufacturingPlacesTags() {
return manufacturingPlacesTags;
}

@JsonProperty("manufacturing_places_tags")
public void setManufacturingPlacesTags(Object manufacturingPlacesTags) {
this.manufacturingPlacesTags = manufacturingPlacesTags;
}

public OpenProduct withManufacturingPlacesTags(Object manufacturingPlacesTags) {
this.manufacturingPlacesTags = manufacturingPlacesTags;
return this;
}

@JsonProperty("labels")
public Object getLabels() {
return labels;
}

@JsonProperty("labels")
public void setLabels(Object labels) {
this.labels = labels;
}

public OpenProduct withLabels(Object labels) {
this.labels = labels;
return this;
}

@JsonProperty("labels_tags")
public Object getLabelsTags() {
return labelsTags;
}

@JsonProperty("labels_tags")
public void setLabelsTags(Object labelsTags) {
this.labelsTags = labelsTags;
}

public OpenProduct withLabelsTags(Object labelsTags) {
this.labelsTags = labelsTags;
return this;
}

@JsonProperty("labels_en")
public Object getLabelsEn() {
return labelsEn;
}

@JsonProperty("labels_en")
public void setLabelsEn(Object labelsEn) {
this.labelsEn = labelsEn;
}

public OpenProduct withLabelsEn(Object labelsEn) {
this.labelsEn = labelsEn;
return this;
}

@JsonProperty("emb_codes")
public Object getEmbCodes() {
return embCodes;
}

@JsonProperty("emb_codes")
public void setEmbCodes(Object embCodes) {
this.embCodes = embCodes;
}

public OpenProduct withEmbCodes(Object embCodes) {
this.embCodes = embCodes;
return this;
}

@JsonProperty("emb_codes_tags")
public Object getEmbCodesTags() {
return embCodesTags;
}

@JsonProperty("emb_codes_tags")
public void setEmbCodesTags(Object embCodesTags) {
this.embCodesTags = embCodesTags;
}

public OpenProduct withEmbCodesTags(Object embCodesTags) {
this.embCodesTags = embCodesTags;
return this;
}

@JsonProperty("first_packaging_code_geo")
public Object getFirstPackagingCodeGeo() {
return firstPackagingCodeGeo;
}

@JsonProperty("first_packaging_code_geo")
public void setFirstPackagingCodeGeo(Object firstPackagingCodeGeo) {
this.firstPackagingCodeGeo = firstPackagingCodeGeo;
}

public OpenProduct withFirstPackagingCodeGeo(Object firstPackagingCodeGeo) {
this.firstPackagingCodeGeo = firstPackagingCodeGeo;
return this;
}

@JsonProperty("cities")
public Object getCities() {
return cities;
}

@JsonProperty("cities")
public void setCities(Object cities) {
this.cities = cities;
}

public OpenProduct withCities(Object cities) {
this.cities = cities;
return this;
}

@JsonProperty("cities_tags")
public Object getCitiesTags() {
return citiesTags;
}

@JsonProperty("cities_tags")
public void setCitiesTags(Object citiesTags) {
this.citiesTags = citiesTags;
}

public OpenProduct withCitiesTags(Object citiesTags) {
this.citiesTags = citiesTags;
return this;
}

@JsonProperty("purchase_places")
public Object getPurchasePlaces() {
return purchasePlaces;
}

@JsonProperty("purchase_places")
public void setPurchasePlaces(Object purchasePlaces) {
this.purchasePlaces = purchasePlaces;
}

public OpenProduct withPurchasePlaces(Object purchasePlaces) {
this.purchasePlaces = purchasePlaces;
return this;
}

@JsonProperty("stores")
public Object getStores() {
return stores;
}

@JsonProperty("stores")
public void setStores(Object stores) {
this.stores = stores;
}

public OpenProduct withStores(Object stores) {
this.stores = stores;
return this;
}

@JsonProperty("countries")
public String getCountries() {
return countries;
}

@JsonProperty("countries")
public void setCountries(String countries) {
this.countries = countries;
}

public OpenProduct withCountries(String countries) {
this.countries = countries;
return this;
}

@JsonProperty("countries_tags")
public String getCountriesTags() {
return countriesTags;
}

@JsonProperty("countries_tags")
public void setCountriesTags(String countriesTags) {
this.countriesTags = countriesTags;
}

public OpenProduct withCountriesTags(String countriesTags) {
this.countriesTags = countriesTags;
return this;
}

@JsonProperty("countries_en")
public String getCountriesEn() {
return countriesEn;
}

@JsonProperty("countries_en")
public void setCountriesEn(String countriesEn) {
this.countriesEn = countriesEn;
}

public OpenProduct withCountriesEn(String countriesEn) {
this.countriesEn = countriesEn;
return this;
}

@JsonProperty("ingredients_text")
public Object getIngredientsText() {
return ingredientsText;
}

@JsonProperty("ingredients_text")
public void setIngredientsText(Object ingredientsText) {
this.ingredientsText = ingredientsText;
}

public OpenProduct withIngredientsText(Object ingredientsText) {
this.ingredientsText = ingredientsText;
return this;
}

@JsonProperty("allergens")
public Object getAllergens() {
return allergens;
}

@JsonProperty("allergens")
public void setAllergens(Object allergens) {
this.allergens = allergens;
}

public OpenProduct withAllergens(Object allergens) {
this.allergens = allergens;
return this;
}

@JsonProperty("allergens_en")
public Object getAllergensEn() {
return allergensEn;
}

@JsonProperty("allergens_en")
public void setAllergensEn(Object allergensEn) {
this.allergensEn = allergensEn;
}

public OpenProduct withAllergensEn(Object allergensEn) {
this.allergensEn = allergensEn;
return this;
}

@JsonProperty("traces")
public Object getTraces() {
return traces;
}

@JsonProperty("traces")
public void setTraces(Object traces) {
this.traces = traces;
}

public OpenProduct withTraces(Object traces) {
this.traces = traces;
return this;
}

@JsonProperty("traces_tags")
public Object getTracesTags() {
return tracesTags;
}

@JsonProperty("traces_tags")
public void setTracesTags(Object tracesTags) {
this.tracesTags = tracesTags;
}

public OpenProduct withTracesTags(Object tracesTags) {
this.tracesTags = tracesTags;
return this;
}

@JsonProperty("traces_en")
public Object getTracesEn() {
return tracesEn;
}

@JsonProperty("traces_en")
public void setTracesEn(Object tracesEn) {
this.tracesEn = tracesEn;
}

public OpenProduct withTracesEn(Object tracesEn) {
this.tracesEn = tracesEn;
return this;
}

@JsonProperty("serving_size")
public Object getServingSize() {
return servingSize;
}

@JsonProperty("serving_size")
public void setServingSize(Object servingSize) {
this.servingSize = servingSize;
}

public OpenProduct withServingSize(Object servingSize) {
this.servingSize = servingSize;
return this;
}

@JsonProperty("no_nutriments")
public Object getNoNutriments() {
return noNutriments;
}

@JsonProperty("no_nutriments")
public void setNoNutriments(Object noNutriments) {
this.noNutriments = noNutriments;
}

public OpenProduct withNoNutriments(Object noNutriments) {
this.noNutriments = noNutriments;
return this;
}

@JsonProperty("additives_n")
public Object getAdditivesN() {
return additivesN;
}

@JsonProperty("additives_n")
public void setAdditivesN(Object additivesN) {
this.additivesN = additivesN;
}

public OpenProduct withAdditivesN(Object additivesN) {
this.additivesN = additivesN;
return this;
}

@JsonProperty("additives")
public Object getAdditives() {
return additives;
}

@JsonProperty("additives")
public void setAdditives(Object additives) {
this.additives = additives;
}

public OpenProduct withAdditives(Object additives) {
this.additives = additives;
return this;
}

@JsonProperty("additives_tags")
public Object getAdditivesTags() {
return additivesTags;
}

@JsonProperty("additives_tags")
public void setAdditivesTags(Object additivesTags) {
this.additivesTags = additivesTags;
}

public OpenProduct withAdditivesTags(Object additivesTags) {
this.additivesTags = additivesTags;
return this;
}

@JsonProperty("additives_en")
public Object getAdditivesEn() {
return additivesEn;
}

@JsonProperty("additives_en")
public void setAdditivesEn(Object additivesEn) {
this.additivesEn = additivesEn;
}

public OpenProduct withAdditivesEn(Object additivesEn) {
this.additivesEn = additivesEn;
return this;
}

@JsonProperty("ingredients_from_palm_oil_n")
public Object getIngredientsFromPalmOilN() {
return ingredientsFromPalmOilN;
}

@JsonProperty("ingredients_from_palm_oil_n")
public void setIngredientsFromPalmOilN(Object ingredientsFromPalmOilN) {
this.ingredientsFromPalmOilN = ingredientsFromPalmOilN;
}

public OpenProduct withIngredientsFromPalmOilN(Object ingredientsFromPalmOilN) {
this.ingredientsFromPalmOilN = ingredientsFromPalmOilN;
return this;
}

@JsonProperty("ingredients_from_palm_oil")
public Object getIngredientsFromPalmOil() {
return ingredientsFromPalmOil;
}

@JsonProperty("ingredients_from_palm_oil")
public void setIngredientsFromPalmOil(Object ingredientsFromPalmOil) {
this.ingredientsFromPalmOil = ingredientsFromPalmOil;
}

public OpenProduct withIngredientsFromPalmOil(Object ingredientsFromPalmOil) {
this.ingredientsFromPalmOil = ingredientsFromPalmOil;
return this;
}

@JsonProperty("ingredients_from_palm_oil_tags")
public Object getIngredientsFromPalmOilTags() {
return ingredientsFromPalmOilTags;
}

@JsonProperty("ingredients_from_palm_oil_tags")
public void setIngredientsFromPalmOilTags(Object ingredientsFromPalmOilTags) {
this.ingredientsFromPalmOilTags = ingredientsFromPalmOilTags;
}

public OpenProduct withIngredientsFromPalmOilTags(Object ingredientsFromPalmOilTags) {
this.ingredientsFromPalmOilTags = ingredientsFromPalmOilTags;
return this;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil_n")
public Object getIngredientsThatMayBeFromPalmOilN() {
return ingredientsThatMayBeFromPalmOilN;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil_n")
public void setIngredientsThatMayBeFromPalmOilN(Object ingredientsThatMayBeFromPalmOilN) {
this.ingredientsThatMayBeFromPalmOilN = ingredientsThatMayBeFromPalmOilN;
}

public OpenProduct withIngredientsThatMayBeFromPalmOilN(Object ingredientsThatMayBeFromPalmOilN) {
this.ingredientsThatMayBeFromPalmOilN = ingredientsThatMayBeFromPalmOilN;
return this;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil")
public Object getIngredientsThatMayBeFromPalmOil() {
return ingredientsThatMayBeFromPalmOil;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil")
public void setIngredientsThatMayBeFromPalmOil(Object ingredientsThatMayBeFromPalmOil) {
this.ingredientsThatMayBeFromPalmOil = ingredientsThatMayBeFromPalmOil;
}

public OpenProduct withIngredientsThatMayBeFromPalmOil(Object ingredientsThatMayBeFromPalmOil) {
this.ingredientsThatMayBeFromPalmOil = ingredientsThatMayBeFromPalmOil;
return this;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil_tags")
public Object getIngredientsThatMayBeFromPalmOilTags() {
return ingredientsThatMayBeFromPalmOilTags;
}

@JsonProperty("ingredients_that_may_be_from_palm_oil_tags")
public void setIngredientsThatMayBeFromPalmOilTags(Object ingredientsThatMayBeFromPalmOilTags) {
this.ingredientsThatMayBeFromPalmOilTags = ingredientsThatMayBeFromPalmOilTags;
}

public OpenProduct withIngredientsThatMayBeFromPalmOilTags(Object ingredientsThatMayBeFromPalmOilTags) {
this.ingredientsThatMayBeFromPalmOilTags = ingredientsThatMayBeFromPalmOilTags;
return this;
}

@JsonProperty("nutrition_grade_uk")
public Object getNutritionGradeUk() {
return nutritionGradeUk;
}

@JsonProperty("nutrition_grade_uk")
public void setNutritionGradeUk(Object nutritionGradeUk) {
this.nutritionGradeUk = nutritionGradeUk;
}

public OpenProduct withNutritionGradeUk(Object nutritionGradeUk) {
this.nutritionGradeUk = nutritionGradeUk;
return this;
}

@JsonProperty("nutrition_grade_fr")
public Object getNutritionGradeFr() {
return nutritionGradeFr;
}

@JsonProperty("nutrition_grade_fr")
public void setNutritionGradeFr(Object nutritionGradeFr) {
this.nutritionGradeFr = nutritionGradeFr;
}

public OpenProduct withNutritionGradeFr(Object nutritionGradeFr) {
this.nutritionGradeFr = nutritionGradeFr;
return this;
}

@JsonProperty("pnns_groups_1")
public Object getPnnsGroups1() {
return pnnsGroups1;
}

@JsonProperty("pnns_groups_1")
public void setPnnsGroups1(Object pnnsGroups1) {
this.pnnsGroups1 = pnnsGroups1;
}

public OpenProduct withPnnsGroups1(Object pnnsGroups1) {
this.pnnsGroups1 = pnnsGroups1;
return this;
}

@JsonProperty("pnns_groups_2")
public Object getPnnsGroups2() {
return pnnsGroups2;
}

@JsonProperty("pnns_groups_2")
public void setPnnsGroups2(Object pnnsGroups2) {
this.pnnsGroups2 = pnnsGroups2;
}

public OpenProduct withPnnsGroups2(Object pnnsGroups2) {
this.pnnsGroups2 = pnnsGroups2;
return this;
}

@JsonProperty("states")
public String getStates() {
return states;
}

@JsonProperty("states")
public void setStates(String states) {
this.states = states;
}

public OpenProduct withStates(String states) {
this.states = states;
return this;
}

@JsonProperty("states_tags")
public String getStatesTags() {
return statesTags;
}

@JsonProperty("states_tags")
public void setStatesTags(String statesTags) {
this.statesTags = statesTags;
}

public OpenProduct withStatesTags(String statesTags) {
this.statesTags = statesTags;
return this;
}

@JsonProperty("states_en")
public String getStatesEn() {
return statesEn;
}

@JsonProperty("states_en")
public void setStatesEn(String statesEn) {
this.statesEn = statesEn;
}

public OpenProduct withStatesEn(String statesEn) {
this.statesEn = statesEn;
return this;
}

@JsonProperty("main_category")
public Object getMainCategory() {
return mainCategory;
}

@JsonProperty("main_category")
public void setMainCategory(Object mainCategory) {
this.mainCategory = mainCategory;
}

public OpenProduct withMainCategory(Object mainCategory) {
this.mainCategory = mainCategory;
return this;
}

@JsonProperty("main_category_en")
public Object getMainCategoryEn() {
return mainCategoryEn;
}

@JsonProperty("main_category_en")
public void setMainCategoryEn(Object mainCategoryEn) {
this.mainCategoryEn = mainCategoryEn;
}

public OpenProduct withMainCategoryEn(Object mainCategoryEn) {
this.mainCategoryEn = mainCategoryEn;
return this;
}

@JsonProperty("image_url")
public Object getImageUrl() {
return imageUrl;
}

@JsonProperty("image_url")
public void setImageUrl(Object imageUrl) {
this.imageUrl = imageUrl;
}

public OpenProduct withImageUrl(Object imageUrl) {
this.imageUrl = imageUrl;
return this;
}

@JsonProperty("image_small_url")
public Object getImageSmallUrl() {
return imageSmallUrl;
}

@JsonProperty("image_small_url")
public void setImageSmallUrl(Object imageSmallUrl) {
this.imageSmallUrl = imageSmallUrl;
}

public OpenProduct withImageSmallUrl(Object imageSmallUrl) {
this.imageSmallUrl = imageSmallUrl;
return this;
}

@JsonProperty("energy_100g")
public Object getEnergy100g() {
return energy100g;
}

@JsonProperty("energy_100g")
public void setEnergy100g(Object energy100g) {
this.energy100g = energy100g;
}

public OpenProduct withEnergy100g(Object energy100g) {
this.energy100g = energy100g;
return this;
}

@JsonProperty("energy-from-fat_100g")
public Object getEnergyFromFat100g() {
return energyFromFat100g;
}

@JsonProperty("energy-from-fat_100g")
public void setEnergyFromFat100g(Object energyFromFat100g) {
this.energyFromFat100g = energyFromFat100g;
}

public OpenProduct withEnergyFromFat100g(Object energyFromFat100g) {
this.energyFromFat100g = energyFromFat100g;
return this;
}

@JsonProperty("fat_100g")
public Object getFat100g() {
return fat100g;
}

@JsonProperty("fat_100g")
public void setFat100g(Object fat100g) {
this.fat100g = fat100g;
}

public OpenProduct withFat100g(Object fat100g) {
this.fat100g = fat100g;
return this;
}

@JsonProperty("saturated-fat_100g")
public Object getSaturatedFat100g() {
return saturatedFat100g;
}

@JsonProperty("saturated-fat_100g")
public void setSaturatedFat100g(Object saturatedFat100g) {
this.saturatedFat100g = saturatedFat100g;
}

public OpenProduct withSaturatedFat100g(Object saturatedFat100g) {
this.saturatedFat100g = saturatedFat100g;
return this;
}

@JsonProperty("-butyric-acid_100g")
public Object getButyricAcid100g() {
return butyricAcid100g;
}

@JsonProperty("-butyric-acid_100g")
public void setButyricAcid100g(Object butyricAcid100g) {
this.butyricAcid100g = butyricAcid100g;
}

public OpenProduct withButyricAcid100g(Object butyricAcid100g) {
this.butyricAcid100g = butyricAcid100g;
return this;
}

@JsonProperty("-caproic-acid_100g")
public Object getCaproicAcid100g() {
return caproicAcid100g;
}

@JsonProperty("-caproic-acid_100g")
public void setCaproicAcid100g(Object caproicAcid100g) {
this.caproicAcid100g = caproicAcid100g;
}

public OpenProduct withCaproicAcid100g(Object caproicAcid100g) {
this.caproicAcid100g = caproicAcid100g;
return this;
}

@JsonProperty("-caprylic-acid_100g")
public Object getCaprylicAcid100g() {
return caprylicAcid100g;
}

@JsonProperty("-caprylic-acid_100g")
public void setCaprylicAcid100g(Object caprylicAcid100g) {
this.caprylicAcid100g = caprylicAcid100g;
}

public OpenProduct withCaprylicAcid100g(Object caprylicAcid100g) {
this.caprylicAcid100g = caprylicAcid100g;
return this;
}

@JsonProperty("-capric-acid_100g")
public Object getCapricAcid100g() {
return capricAcid100g;
}

@JsonProperty("-capric-acid_100g")
public void setCapricAcid100g(Object capricAcid100g) {
this.capricAcid100g = capricAcid100g;
}

public OpenProduct withCapricAcid100g(Object capricAcid100g) {
this.capricAcid100g = capricAcid100g;
return this;
}

@JsonProperty("-lauric-acid_100g")
public Object getLauricAcid100g() {
return lauricAcid100g;
}

@JsonProperty("-lauric-acid_100g")
public void setLauricAcid100g(Object lauricAcid100g) {
this.lauricAcid100g = lauricAcid100g;
}

public OpenProduct withLauricAcid100g(Object lauricAcid100g) {
this.lauricAcid100g = lauricAcid100g;
return this;
}

@JsonProperty("-myristic-acid_100g")
public Object getMyristicAcid100g() {
return myristicAcid100g;
}

@JsonProperty("-myristic-acid_100g")
public void setMyristicAcid100g(Object myristicAcid100g) {
this.myristicAcid100g = myristicAcid100g;
}

public OpenProduct withMyristicAcid100g(Object myristicAcid100g) {
this.myristicAcid100g = myristicAcid100g;
return this;
}

@JsonProperty("-palmitic-acid_100g")
public Object getPalmiticAcid100g() {
return palmiticAcid100g;
}

@JsonProperty("-palmitic-acid_100g")
public void setPalmiticAcid100g(Object palmiticAcid100g) {
this.palmiticAcid100g = palmiticAcid100g;
}

public OpenProduct withPalmiticAcid100g(Object palmiticAcid100g) {
this.palmiticAcid100g = palmiticAcid100g;
return this;
}

@JsonProperty("-stearic-acid_100g")
public Object getStearicAcid100g() {
return stearicAcid100g;
}

@JsonProperty("-stearic-acid_100g")
public void setStearicAcid100g(Object stearicAcid100g) {
this.stearicAcid100g = stearicAcid100g;
}

public OpenProduct withStearicAcid100g(Object stearicAcid100g) {
this.stearicAcid100g = stearicAcid100g;
return this;
}

@JsonProperty("-arachidic-acid_100g")
public Object getArachidicAcid100g() {
return arachidicAcid100g;
}

@JsonProperty("-arachidic-acid_100g")
public void setArachidicAcid100g(Object arachidicAcid100g) {
this.arachidicAcid100g = arachidicAcid100g;
}

public OpenProduct withArachidicAcid100g(Object arachidicAcid100g) {
this.arachidicAcid100g = arachidicAcid100g;
return this;
}

@JsonProperty("-behenic-acid_100g")
public Object getBehenicAcid100g() {
return behenicAcid100g;
}

@JsonProperty("-behenic-acid_100g")
public void setBehenicAcid100g(Object behenicAcid100g) {
this.behenicAcid100g = behenicAcid100g;
}

public OpenProduct withBehenicAcid100g(Object behenicAcid100g) {
this.behenicAcid100g = behenicAcid100g;
return this;
}

@JsonProperty("-lignoceric-acid_100g")
public Object getLignocericAcid100g() {
return lignocericAcid100g;
}

@JsonProperty("-lignoceric-acid_100g")
public void setLignocericAcid100g(Object lignocericAcid100g) {
this.lignocericAcid100g = lignocericAcid100g;
}

public OpenProduct withLignocericAcid100g(Object lignocericAcid100g) {
this.lignocericAcid100g = lignocericAcid100g;
return this;
}

@JsonProperty("-cerotic-acid_100g")
public Object getCeroticAcid100g() {
return ceroticAcid100g;
}

@JsonProperty("-cerotic-acid_100g")
public void setCeroticAcid100g(Object ceroticAcid100g) {
this.ceroticAcid100g = ceroticAcid100g;
}

public OpenProduct withCeroticAcid100g(Object ceroticAcid100g) {
this.ceroticAcid100g = ceroticAcid100g;
return this;
}

@JsonProperty("-montanic-acid_100g")
public Object getMontanicAcid100g() {
return montanicAcid100g;
}

@JsonProperty("-montanic-acid_100g")
public void setMontanicAcid100g(Object montanicAcid100g) {
this.montanicAcid100g = montanicAcid100g;
}

public OpenProduct withMontanicAcid100g(Object montanicAcid100g) {
this.montanicAcid100g = montanicAcid100g;
return this;
}

@JsonProperty("-melissic-acid_100g")
public Object getMelissicAcid100g() {
return melissicAcid100g;
}

@JsonProperty("-melissic-acid_100g")
public void setMelissicAcid100g(Object melissicAcid100g) {
this.melissicAcid100g = melissicAcid100g;
}

public OpenProduct withMelissicAcid100g(Object melissicAcid100g) {
this.melissicAcid100g = melissicAcid100g;
return this;
}

@JsonProperty("monounsaturated-fat_100g")
public Object getMonounsaturatedFat100g() {
return monounsaturatedFat100g;
}

@JsonProperty("monounsaturated-fat_100g")
public void setMonounsaturatedFat100g(Object monounsaturatedFat100g) {
this.monounsaturatedFat100g = monounsaturatedFat100g;
}

public OpenProduct withMonounsaturatedFat100g(Object monounsaturatedFat100g) {
this.monounsaturatedFat100g = monounsaturatedFat100g;
return this;
}

@JsonProperty("polyunsaturated-fat_100g")
public Object getPolyunsaturatedFat100g() {
return polyunsaturatedFat100g;
}

@JsonProperty("polyunsaturated-fat_100g")
public void setPolyunsaturatedFat100g(Object polyunsaturatedFat100g) {
this.polyunsaturatedFat100g = polyunsaturatedFat100g;
}

public OpenProduct withPolyunsaturatedFat100g(Object polyunsaturatedFat100g) {
this.polyunsaturatedFat100g = polyunsaturatedFat100g;
return this;
}

@JsonProperty("omega-3-fat_100g")
public Object getOmega3Fat100g() {
return omega3Fat100g;
}

@JsonProperty("omega-3-fat_100g")
public void setOmega3Fat100g(Object omega3Fat100g) {
this.omega3Fat100g = omega3Fat100g;
}

public OpenProduct withOmega3Fat100g(Object omega3Fat100g) {
this.omega3Fat100g = omega3Fat100g;
return this;
}

@JsonProperty("-alpha-linolenic-acid_100g")
public Object getAlphaLinolenicAcid100g() {
return alphaLinolenicAcid100g;
}

@JsonProperty("-alpha-linolenic-acid_100g")
public void setAlphaLinolenicAcid100g(Object alphaLinolenicAcid100g) {
this.alphaLinolenicAcid100g = alphaLinolenicAcid100g;
}

public OpenProduct withAlphaLinolenicAcid100g(Object alphaLinolenicAcid100g) {
this.alphaLinolenicAcid100g = alphaLinolenicAcid100g;
return this;
}

@JsonProperty("-eicosapentaenoic-acid_100g")
public Object getEicosapentaenoicAcid100g() {
return eicosapentaenoicAcid100g;
}

@JsonProperty("-eicosapentaenoic-acid_100g")
public void setEicosapentaenoicAcid100g(Object eicosapentaenoicAcid100g) {
this.eicosapentaenoicAcid100g = eicosapentaenoicAcid100g;
}

public OpenProduct withEicosapentaenoicAcid100g(Object eicosapentaenoicAcid100g) {
this.eicosapentaenoicAcid100g = eicosapentaenoicAcid100g;
return this;
}

@JsonProperty("-docosahexaenoic-acid_100g")
public Object getDocosahexaenoicAcid100g() {
return docosahexaenoicAcid100g;
}

@JsonProperty("-docosahexaenoic-acid_100g")
public void setDocosahexaenoicAcid100g(Object docosahexaenoicAcid100g) {
this.docosahexaenoicAcid100g = docosahexaenoicAcid100g;
}

public OpenProduct withDocosahexaenoicAcid100g(Object docosahexaenoicAcid100g) {
this.docosahexaenoicAcid100g = docosahexaenoicAcid100g;
return this;
}

@JsonProperty("omega-6-fat_100g")
public Object getOmega6Fat100g() {
return omega6Fat100g;
}

@JsonProperty("omega-6-fat_100g")
public void setOmega6Fat100g(Object omega6Fat100g) {
this.omega6Fat100g = omega6Fat100g;
}

public OpenProduct withOmega6Fat100g(Object omega6Fat100g) {
this.omega6Fat100g = omega6Fat100g;
return this;
}

@JsonProperty("-linoleic-acid_100g")
public Object getLinoleicAcid100g() {
return linoleicAcid100g;
}

@JsonProperty("-linoleic-acid_100g")
public void setLinoleicAcid100g(Object linoleicAcid100g) {
this.linoleicAcid100g = linoleicAcid100g;
}

public OpenProduct withLinoleicAcid100g(Object linoleicAcid100g) {
this.linoleicAcid100g = linoleicAcid100g;
return this;
}

@JsonProperty("-arachidonic-acid_100g")
public Object getArachidonicAcid100g() {
return arachidonicAcid100g;
}

@JsonProperty("-arachidonic-acid_100g")
public void setArachidonicAcid100g(Object arachidonicAcid100g) {
this.arachidonicAcid100g = arachidonicAcid100g;
}

public OpenProduct withArachidonicAcid100g(Object arachidonicAcid100g) {
this.arachidonicAcid100g = arachidonicAcid100g;
return this;
}

@JsonProperty("-gamma-linolenic-acid_100g")
public Object getGammaLinolenicAcid100g() {
return gammaLinolenicAcid100g;
}

@JsonProperty("-gamma-linolenic-acid_100g")
public void setGammaLinolenicAcid100g(Object gammaLinolenicAcid100g) {
this.gammaLinolenicAcid100g = gammaLinolenicAcid100g;
}

public OpenProduct withGammaLinolenicAcid100g(Object gammaLinolenicAcid100g) {
this.gammaLinolenicAcid100g = gammaLinolenicAcid100g;
return this;
}

@JsonProperty("-dihomo-gamma-linolenic-acid_100g")
public Object getDihomoGammaLinolenicAcid100g() {
return dihomoGammaLinolenicAcid100g;
}

@JsonProperty("-dihomo-gamma-linolenic-acid_100g")
public void setDihomoGammaLinolenicAcid100g(Object dihomoGammaLinolenicAcid100g) {
this.dihomoGammaLinolenicAcid100g = dihomoGammaLinolenicAcid100g;
}

public OpenProduct withDihomoGammaLinolenicAcid100g(Object dihomoGammaLinolenicAcid100g) {
this.dihomoGammaLinolenicAcid100g = dihomoGammaLinolenicAcid100g;
return this;
}

@JsonProperty("omega-9-fat_100g")
public Object getOmega9Fat100g() {
return omega9Fat100g;
}

@JsonProperty("omega-9-fat_100g")
public void setOmega9Fat100g(Object omega9Fat100g) {
this.omega9Fat100g = omega9Fat100g;
}

public OpenProduct withOmega9Fat100g(Object omega9Fat100g) {
this.omega9Fat100g = omega9Fat100g;
return this;
}

@JsonProperty("-oleic-acid_100g")
public Object getOleicAcid100g() {
return oleicAcid100g;
}

@JsonProperty("-oleic-acid_100g")
public void setOleicAcid100g(Object oleicAcid100g) {
this.oleicAcid100g = oleicAcid100g;
}

public OpenProduct withOleicAcid100g(Object oleicAcid100g) {
this.oleicAcid100g = oleicAcid100g;
return this;
}

@JsonProperty("-elaidic-acid_100g")
public Object getElaidicAcid100g() {
return elaidicAcid100g;
}

@JsonProperty("-elaidic-acid_100g")
public void setElaidicAcid100g(Object elaidicAcid100g) {
this.elaidicAcid100g = elaidicAcid100g;
}

public OpenProduct withElaidicAcid100g(Object elaidicAcid100g) {
this.elaidicAcid100g = elaidicAcid100g;
return this;
}

@JsonProperty("-gondoic-acid_100g")
public Object getGondoicAcid100g() {
return gondoicAcid100g;
}

@JsonProperty("-gondoic-acid_100g")
public void setGondoicAcid100g(Object gondoicAcid100g) {
this.gondoicAcid100g = gondoicAcid100g;
}

public OpenProduct withGondoicAcid100g(Object gondoicAcid100g) {
this.gondoicAcid100g = gondoicAcid100g;
return this;
}

@JsonProperty("-mead-acid_100g")
public Object getMeadAcid100g() {
return meadAcid100g;
}

@JsonProperty("-mead-acid_100g")
public void setMeadAcid100g(Object meadAcid100g) {
this.meadAcid100g = meadAcid100g;
}

public OpenProduct withMeadAcid100g(Object meadAcid100g) {
this.meadAcid100g = meadAcid100g;
return this;
}

@JsonProperty("-erucic-acid_100g")
public Object getErucicAcid100g() {
return erucicAcid100g;
}

@JsonProperty("-erucic-acid_100g")
public void setErucicAcid100g(Object erucicAcid100g) {
this.erucicAcid100g = erucicAcid100g;
}

public OpenProduct withErucicAcid100g(Object erucicAcid100g) {
this.erucicAcid100g = erucicAcid100g;
return this;
}

@JsonProperty("-nervonic-acid_100g")
public Object getNervonicAcid100g() {
return nervonicAcid100g;
}

@JsonProperty("-nervonic-acid_100g")
public void setNervonicAcid100g(Object nervonicAcid100g) {
this.nervonicAcid100g = nervonicAcid100g;
}

public OpenProduct withNervonicAcid100g(Object nervonicAcid100g) {
this.nervonicAcid100g = nervonicAcid100g;
return this;
}

@JsonProperty("trans-fat_100g")
public Object getTransFat100g() {
return transFat100g;
}

@JsonProperty("trans-fat_100g")
public void setTransFat100g(Object transFat100g) {
this.transFat100g = transFat100g;
}

public OpenProduct withTransFat100g(Object transFat100g) {
this.transFat100g = transFat100g;
return this;
}

@JsonProperty("cholesterol_100g")
public Object getCholesterol100g() {
return cholesterol100g;
}

@JsonProperty("cholesterol_100g")
public void setCholesterol100g(Object cholesterol100g) {
this.cholesterol100g = cholesterol100g;
}

public OpenProduct withCholesterol100g(Object cholesterol100g) {
this.cholesterol100g = cholesterol100g;
return this;
}

@JsonProperty("carbohydrates_100g")
public Object getCarbohydrates100g() {
return carbohydrates100g;
}

@JsonProperty("carbohydrates_100g")
public void setCarbohydrates100g(Object carbohydrates100g) {
this.carbohydrates100g = carbohydrates100g;
}

public OpenProduct withCarbohydrates100g(Object carbohydrates100g) {
this.carbohydrates100g = carbohydrates100g;
return this;
}

@JsonProperty("sugars_100g")
public Object getSugars100g() {
return sugars100g;
}

@JsonProperty("sugars_100g")
public void setSugars100g(Object sugars100g) {
this.sugars100g = sugars100g;
}

public OpenProduct withSugars100g(Object sugars100g) {
this.sugars100g = sugars100g;
return this;
}

@JsonProperty("-sucrose_100g")
public Object getSucrose100g() {
return sucrose100g;
}

@JsonProperty("-sucrose_100g")
public void setSucrose100g(Object sucrose100g) {
this.sucrose100g = sucrose100g;
}

public OpenProduct withSucrose100g(Object sucrose100g) {
this.sucrose100g = sucrose100g;
return this;
}

@JsonProperty("-glucose_100g")
public Object getGlucose100g() {
return glucose100g;
}

@JsonProperty("-glucose_100g")
public void setGlucose100g(Object glucose100g) {
this.glucose100g = glucose100g;
}

public OpenProduct withGlucose100g(Object glucose100g) {
this.glucose100g = glucose100g;
return this;
}

@JsonProperty("-fructose_100g")
public Object getFructose100g() {
return fructose100g;
}

@JsonProperty("-fructose_100g")
public void setFructose100g(Object fructose100g) {
this.fructose100g = fructose100g;
}

public OpenProduct withFructose100g(Object fructose100g) {
this.fructose100g = fructose100g;
return this;
}

@JsonProperty("-lactose_100g")
public Object getLactose100g() {
return lactose100g;
}

@JsonProperty("-lactose_100g")
public void setLactose100g(Object lactose100g) {
this.lactose100g = lactose100g;
}

public OpenProduct withLactose100g(Object lactose100g) {
this.lactose100g = lactose100g;
return this;
}

@JsonProperty("-maltose_100g")
public Object getMaltose100g() {
return maltose100g;
}

@JsonProperty("-maltose_100g")
public void setMaltose100g(Object maltose100g) {
this.maltose100g = maltose100g;
}

public OpenProduct withMaltose100g(Object maltose100g) {
this.maltose100g = maltose100g;
return this;
}

@JsonProperty("-maltodextrins_100g")
public Object getMaltodextrins100g() {
return maltodextrins100g;
}

@JsonProperty("-maltodextrins_100g")
public void setMaltodextrins100g(Object maltodextrins100g) {
this.maltodextrins100g = maltodextrins100g;
}

public OpenProduct withMaltodextrins100g(Object maltodextrins100g) {
this.maltodextrins100g = maltodextrins100g;
return this;
}

@JsonProperty("starch_100g")
public Object getStarch100g() {
return starch100g;
}

@JsonProperty("starch_100g")
public void setStarch100g(Object starch100g) {
this.starch100g = starch100g;
}

public OpenProduct withStarch100g(Object starch100g) {
this.starch100g = starch100g;
return this;
}

@JsonProperty("polyols_100g")
public Object getPolyols100g() {
return polyols100g;
}

@JsonProperty("polyols_100g")
public void setPolyols100g(Object polyols100g) {
this.polyols100g = polyols100g;
}

public OpenProduct withPolyols100g(Object polyols100g) {
this.polyols100g = polyols100g;
return this;
}

@JsonProperty("fiber_100g")
public Object getFiber100g() {
return fiber100g;
}

@JsonProperty("fiber_100g")
public void setFiber100g(Object fiber100g) {
this.fiber100g = fiber100g;
}

public OpenProduct withFiber100g(Object fiber100g) {
this.fiber100g = fiber100g;
return this;
}

@JsonProperty("proteins_100g")
public Object getProteins100g() {
return proteins100g;
}

@JsonProperty("proteins_100g")
public void setProteins100g(Object proteins100g) {
this.proteins100g = proteins100g;
}

public OpenProduct withProteins100g(Object proteins100g) {
this.proteins100g = proteins100g;
return this;
}

@JsonProperty("casein_100g")
public Object getCasein100g() {
return casein100g;
}

@JsonProperty("casein_100g")
public void setCasein100g(Object casein100g) {
this.casein100g = casein100g;
}

public OpenProduct withCasein100g(Object casein100g) {
this.casein100g = casein100g;
return this;
}

@JsonProperty("serum-proteins_100g")
public Object getSerumProteins100g() {
return serumProteins100g;
}

@JsonProperty("serum-proteins_100g")
public void setSerumProteins100g(Object serumProteins100g) {
this.serumProteins100g = serumProteins100g;
}

public OpenProduct withSerumProteins100g(Object serumProteins100g) {
this.serumProteins100g = serumProteins100g;
return this;
}

@JsonProperty("nucleotides_100g")
public Object getNucleotides100g() {
return nucleotides100g;
}

@JsonProperty("nucleotides_100g")
public void setNucleotides100g(Object nucleotides100g) {
this.nucleotides100g = nucleotides100g;
}

public OpenProduct withNucleotides100g(Object nucleotides100g) {
this.nucleotides100g = nucleotides100g;
return this;
}

@JsonProperty("salt_100g")
public Object getSalt100g() {
return salt100g;
}

@JsonProperty("salt_100g")
public void setSalt100g(Object salt100g) {
this.salt100g = salt100g;
}

public OpenProduct withSalt100g(Object salt100g) {
this.salt100g = salt100g;
return this;
}

@JsonProperty("sodium_100g")
public Object getSodium100g() {
return sodium100g;
}

@JsonProperty("sodium_100g")
public void setSodium100g(Object sodium100g) {
this.sodium100g = sodium100g;
}

public OpenProduct withSodium100g(Object sodium100g) {
this.sodium100g = sodium100g;
return this;
}

@JsonProperty("alcohol_100g")
public Object getAlcohol100g() {
return alcohol100g;
}

@JsonProperty("alcohol_100g")
public void setAlcohol100g(Object alcohol100g) {
this.alcohol100g = alcohol100g;
}

public OpenProduct withAlcohol100g(Object alcohol100g) {
this.alcohol100g = alcohol100g;
return this;
}

@JsonProperty("vitamin-a_100g")
public Object getVitaminA100g() {
return vitaminA100g;
}

@JsonProperty("vitamin-a_100g")
public void setVitaminA100g(Object vitaminA100g) {
this.vitaminA100g = vitaminA100g;
}

public OpenProduct withVitaminA100g(Object vitaminA100g) {
this.vitaminA100g = vitaminA100g;
return this;
}

@JsonProperty("beta-carotene_100g")
public Object getBetaCarotene100g() {
return betaCarotene100g;
}

@JsonProperty("beta-carotene_100g")
public void setBetaCarotene100g(Object betaCarotene100g) {
this.betaCarotene100g = betaCarotene100g;
}

public OpenProduct withBetaCarotene100g(Object betaCarotene100g) {
this.betaCarotene100g = betaCarotene100g;
return this;
}

@JsonProperty("vitamin-d_100g")
public Object getVitaminD100g() {
return vitaminD100g;
}

@JsonProperty("vitamin-d_100g")
public void setVitaminD100g(Object vitaminD100g) {
this.vitaminD100g = vitaminD100g;
}

public OpenProduct withVitaminD100g(Object vitaminD100g) {
this.vitaminD100g = vitaminD100g;
return this;
}

@JsonProperty("vitamin-e_100g")
public Object getVitaminE100g() {
return vitaminE100g;
}

@JsonProperty("vitamin-e_100g")
public void setVitaminE100g(Object vitaminE100g) {
this.vitaminE100g = vitaminE100g;
}

public OpenProduct withVitaminE100g(Object vitaminE100g) {
this.vitaminE100g = vitaminE100g;
return this;
}

@JsonProperty("vitamin-k_100g")
public Object getVitaminK100g() {
return vitaminK100g;
}

@JsonProperty("vitamin-k_100g")
public void setVitaminK100g(Object vitaminK100g) {
this.vitaminK100g = vitaminK100g;
}

public OpenProduct withVitaminK100g(Object vitaminK100g) {
this.vitaminK100g = vitaminK100g;
return this;
}

@JsonProperty("vitamin-c_100g")
public Object getVitaminC100g() {
return vitaminC100g;
}

@JsonProperty("vitamin-c_100g")
public void setVitaminC100g(Object vitaminC100g) {
this.vitaminC100g = vitaminC100g;
}

public OpenProduct withVitaminC100g(Object vitaminC100g) {
this.vitaminC100g = vitaminC100g;
return this;
}

@JsonProperty("vitamin-b1_100g")
public Object getVitaminB1100g() {
return vitaminB1100g;
}

@JsonProperty("vitamin-b1_100g")
public void setVitaminB1100g(Object vitaminB1100g) {
this.vitaminB1100g = vitaminB1100g;
}

public OpenProduct withVitaminB1100g(Object vitaminB1100g) {
this.vitaminB1100g = vitaminB1100g;
return this;
}

@JsonProperty("vitamin-b2_100g")
public Object getVitaminB2100g() {
return vitaminB2100g;
}

@JsonProperty("vitamin-b2_100g")
public void setVitaminB2100g(Object vitaminB2100g) {
this.vitaminB2100g = vitaminB2100g;
}

public OpenProduct withVitaminB2100g(Object vitaminB2100g) {
this.vitaminB2100g = vitaminB2100g;
return this;
}

@JsonProperty("vitamin-pp_100g")
public Object getVitaminPp100g() {
return vitaminPp100g;
}

@JsonProperty("vitamin-pp_100g")
public void setVitaminPp100g(Object vitaminPp100g) {
this.vitaminPp100g = vitaminPp100g;
}

public OpenProduct withVitaminPp100g(Object vitaminPp100g) {
this.vitaminPp100g = vitaminPp100g;
return this;
}

@JsonProperty("vitamin-b6_100g")
public Object getVitaminB6100g() {
return vitaminB6100g;
}

@JsonProperty("vitamin-b6_100g")
public void setVitaminB6100g(Object vitaminB6100g) {
this.vitaminB6100g = vitaminB6100g;
}

public OpenProduct withVitaminB6100g(Object vitaminB6100g) {
this.vitaminB6100g = vitaminB6100g;
return this;
}

@JsonProperty("vitamin-b9_100g")
public Object getVitaminB9100g() {
return vitaminB9100g;
}

@JsonProperty("vitamin-b9_100g")
public void setVitaminB9100g(Object vitaminB9100g) {
this.vitaminB9100g = vitaminB9100g;
}

public OpenProduct withVitaminB9100g(Object vitaminB9100g) {
this.vitaminB9100g = vitaminB9100g;
return this;
}

@JsonProperty("folates_100g")
public Object getFolates100g() {
return folates100g;
}

@JsonProperty("folates_100g")
public void setFolates100g(Object folates100g) {
this.folates100g = folates100g;
}

public OpenProduct withFolates100g(Object folates100g) {
this.folates100g = folates100g;
return this;
}

@JsonProperty("vitamin-b12_100g")
public Object getVitaminB12100g() {
return vitaminB12100g;
}

@JsonProperty("vitamin-b12_100g")
public void setVitaminB12100g(Object vitaminB12100g) {
this.vitaminB12100g = vitaminB12100g;
}

public OpenProduct withVitaminB12100g(Object vitaminB12100g) {
this.vitaminB12100g = vitaminB12100g;
return this;
}

@JsonProperty("biotin_100g")
public Object getBiotin100g() {
return biotin100g;
}

@JsonProperty("biotin_100g")
public void setBiotin100g(Object biotin100g) {
this.biotin100g = biotin100g;
}

public OpenProduct withBiotin100g(Object biotin100g) {
this.biotin100g = biotin100g;
return this;
}

@JsonProperty("pantothenic-acid_100g")
public Object getPantothenicAcid100g() {
return pantothenicAcid100g;
}

@JsonProperty("pantothenic-acid_100g")
public void setPantothenicAcid100g(Object pantothenicAcid100g) {
this.pantothenicAcid100g = pantothenicAcid100g;
}

public OpenProduct withPantothenicAcid100g(Object pantothenicAcid100g) {
this.pantothenicAcid100g = pantothenicAcid100g;
return this;
}

@JsonProperty("silica_100g")
public Object getSilica100g() {
return silica100g;
}

@JsonProperty("silica_100g")
public void setSilica100g(Object silica100g) {
this.silica100g = silica100g;
}

public OpenProduct withSilica100g(Object silica100g) {
this.silica100g = silica100g;
return this;
}

@JsonProperty("bicarbonate_100g")
public Object getBicarbonate100g() {
return bicarbonate100g;
}

@JsonProperty("bicarbonate_100g")
public void setBicarbonate100g(Object bicarbonate100g) {
this.bicarbonate100g = bicarbonate100g;
}

public OpenProduct withBicarbonate100g(Object bicarbonate100g) {
this.bicarbonate100g = bicarbonate100g;
return this;
}

@JsonProperty("potassium_100g")
public Object getPotassium100g() {
return potassium100g;
}

@JsonProperty("potassium_100g")
public void setPotassium100g(Object potassium100g) {
this.potassium100g = potassium100g;
}

public OpenProduct withPotassium100g(Object potassium100g) {
this.potassium100g = potassium100g;
return this;
}

@JsonProperty("chloride_100g")
public Object getChloride100g() {
return chloride100g;
}

@JsonProperty("chloride_100g")
public void setChloride100g(Object chloride100g) {
this.chloride100g = chloride100g;
}

public OpenProduct withChloride100g(Object chloride100g) {
this.chloride100g = chloride100g;
return this;
}

@JsonProperty("calcium_100g")
public Object getCalcium100g() {
return calcium100g;
}

@JsonProperty("calcium_100g")
public void setCalcium100g(Object calcium100g) {
this.calcium100g = calcium100g;
}

public OpenProduct withCalcium100g(Object calcium100g) {
this.calcium100g = calcium100g;
return this;
}

@JsonProperty("phosphorus_100g")
public Object getPhosphorus100g() {
return phosphorus100g;
}

@JsonProperty("phosphorus_100g")
public void setPhosphorus100g(Object phosphorus100g) {
this.phosphorus100g = phosphorus100g;
}

public OpenProduct withPhosphorus100g(Object phosphorus100g) {
this.phosphorus100g = phosphorus100g;
return this;
}

@JsonProperty("iron_100g")
public Object getIron100g() {
return iron100g;
}

@JsonProperty("iron_100g")
public void setIron100g(Object iron100g) {
this.iron100g = iron100g;
}

public OpenProduct withIron100g(Object iron100g) {
this.iron100g = iron100g;
return this;
}

@JsonProperty("magnesium_100g")
public Object getMagnesium100g() {
return magnesium100g;
}

@JsonProperty("magnesium_100g")
public void setMagnesium100g(Object magnesium100g) {
this.magnesium100g = magnesium100g;
}

public OpenProduct withMagnesium100g(Object magnesium100g) {
this.magnesium100g = magnesium100g;
return this;
}

@JsonProperty("zinc_100g")
public Object getZinc100g() {
return zinc100g;
}

@JsonProperty("zinc_100g")
public void setZinc100g(Object zinc100g) {
this.zinc100g = zinc100g;
}

public OpenProduct withZinc100g(Object zinc100g) {
this.zinc100g = zinc100g;
return this;
}

@JsonProperty("copper_100g")
public String getCopper100g() {
return copper100g;
}

@JsonProperty("copper_100g")
public void setCopper100g(String copper100g) {
this.copper100g = copper100g;
}

public OpenProduct withCopper100g(String copper100g) {
this.copper100g = copper100g;
return this;
}

@JsonProperty("manganese_100g")
public String getManganese100g() {
return manganese100g;
}

@JsonProperty("manganese_100g")
public void setManganese100g(String manganese100g) {
this.manganese100g = manganese100g;
}

public OpenProduct withManganese100g(String manganese100g) {
this.manganese100g = manganese100g;
return this;
}

@JsonProperty("fluoride_100g")
public String getFluoride100g() {
return fluoride100g;
}

@JsonProperty("fluoride_100g")
public void setFluoride100g(String fluoride100g) {
this.fluoride100g = fluoride100g;
}

public OpenProduct withFluoride100g(String fluoride100g) {
this.fluoride100g = fluoride100g;
return this;
}

@JsonProperty("selenium_100g")
public String getSelenium100g() {
return selenium100g;
}

@JsonProperty("selenium_100g")
public void setSelenium100g(String selenium100g) {
this.selenium100g = selenium100g;
}

public OpenProduct withSelenium100g(String selenium100g) {
this.selenium100g = selenium100g;
return this;
}

@JsonProperty("chromium_100g")
public String getChromium100g() {
return chromium100g;
}

@JsonProperty("chromium_100g")
public void setChromium100g(String chromium100g) {
this.chromium100g = chromium100g;
}

public OpenProduct withChromium100g(String chromium100g) {
this.chromium100g = chromium100g;
return this;
}

@JsonProperty("molybdenum_100g")
public String getMolybdenum100g() {
return molybdenum100g;
}

@JsonProperty("molybdenum_100g")
public void setMolybdenum100g(String molybdenum100g) {
this.molybdenum100g = molybdenum100g;
}

public OpenProduct withMolybdenum100g(String molybdenum100g) {
this.molybdenum100g = molybdenum100g;
return this;
}

@JsonProperty("iodine_100g")
public String getIodine100g() {
return iodine100g;
}

@JsonProperty("iodine_100g")
public void setIodine100g(String iodine100g) {
this.iodine100g = iodine100g;
}

public OpenProduct withIodine100g(String iodine100g) {
this.iodine100g = iodine100g;
return this;
}

@JsonProperty("caffeine_100g")
public String getCaffeine100g() {
return caffeine100g;
}

@JsonProperty("caffeine_100g")
public void setCaffeine100g(String caffeine100g) {
this.caffeine100g = caffeine100g;
}

public OpenProduct withCaffeine100g(String caffeine100g) {
this.caffeine100g = caffeine100g;
return this;
}

@JsonProperty("taurine_100g")
public String getTaurine100g() {
return taurine100g;
}

@JsonProperty("taurine_100g")
public void setTaurine100g(String taurine100g) {
this.taurine100g = taurine100g;
}

public OpenProduct withTaurine100g(String taurine100g) {
this.taurine100g = taurine100g;
return this;
}

@JsonProperty("ph_100g")
public String getPh100g() {
return ph100g;
}

@JsonProperty("ph_100g")
public void setPh100g(String ph100g) {
this.ph100g = ph100g;
}

public OpenProduct withPh100g(String ph100g) {
this.ph100g = ph100g;
return this;
}

@JsonProperty("fruits-vegetables-nuts_100g")
public String getFruitsVegetablesNuts100g() {
return fruitsVegetablesNuts100g;
}

@JsonProperty("fruits-vegetables-nuts_100g")
public void setFruitsVegetablesNuts100g(String fruitsVegetablesNuts100g) {
this.fruitsVegetablesNuts100g = fruitsVegetablesNuts100g;
}

public OpenProduct withFruitsVegetablesNuts100g(String fruitsVegetablesNuts100g) {
this.fruitsVegetablesNuts100g = fruitsVegetablesNuts100g;
return this;
}

@JsonProperty("fruits-vegetables-nuts-estimate_100g")
public String getFruitsVegetablesNutsEstimate100g() {
return fruitsVegetablesNutsEstimate100g;
}

@JsonProperty("fruits-vegetables-nuts-estimate_100g")
public void setFruitsVegetablesNutsEstimate100g(String fruitsVegetablesNutsEstimate100g) {
this.fruitsVegetablesNutsEstimate100g = fruitsVegetablesNutsEstimate100g;
}

public OpenProduct withFruitsVegetablesNutsEstimate100g(String fruitsVegetablesNutsEstimate100g) {
this.fruitsVegetablesNutsEstimate100g = fruitsVegetablesNutsEstimate100g;
return this;
}

@JsonProperty("collagen-meat-protein-ratio_100g")
public String getCollagenMeatProteinRatio100g() {
return collagenMeatProteinRatio100g;
}

@JsonProperty("collagen-meat-protein-ratio_100g")
public void setCollagenMeatProteinRatio100g(String collagenMeatProteinRatio100g) {
this.collagenMeatProteinRatio100g = collagenMeatProteinRatio100g;
}

public OpenProduct withCollagenMeatProteinRatio100g(String collagenMeatProteinRatio100g) {
this.collagenMeatProteinRatio100g = collagenMeatProteinRatio100g;
return this;
}

@JsonProperty("cocoa_100g")
public String getCocoa100g() {
return cocoa100g;
}

@JsonProperty("cocoa_100g")
public void setCocoa100g(String cocoa100g) {
this.cocoa100g = cocoa100g;
}

public OpenProduct withCocoa100g(String cocoa100g) {
this.cocoa100g = cocoa100g;
return this;
}

@JsonProperty("chlorophyl_100g")
public String getChlorophyl100g() {
return chlorophyl100g;
}

@JsonProperty("chlorophyl_100g")
public void setChlorophyl100g(String chlorophyl100g) {
this.chlorophyl100g = chlorophyl100g;
}

public OpenProduct withChlorophyl100g(String chlorophyl100g) {
this.chlorophyl100g = chlorophyl100g;
return this;
}

@JsonProperty("carbon-footprint_100g")
public String getCarbonFootprint100g() {
return carbonFootprint100g;
}

@JsonProperty("carbon-footprint_100g")
public void setCarbonFootprint100g(String carbonFootprint100g) {
this.carbonFootprint100g = carbonFootprint100g;
}

public OpenProduct withCarbonFootprint100g(String carbonFootprint100g) {
this.carbonFootprint100g = carbonFootprint100g;
return this;
}

@JsonProperty("nutrition-score-fr_100g")
public String getNutritionScoreFr100g() {
return nutritionScoreFr100g;
}

@JsonProperty("nutrition-score-fr_100g")
public void setNutritionScoreFr100g(String nutritionScoreFr100g) {
this.nutritionScoreFr100g = nutritionScoreFr100g;
}

public OpenProduct withNutritionScoreFr100g(String nutritionScoreFr100g) {
this.nutritionScoreFr100g = nutritionScoreFr100g;
return this;
}

@JsonProperty("nutrition-score-uk_100g")
public String getNutritionScoreUk100g() {
return nutritionScoreUk100g;
}

@JsonProperty("nutrition-score-uk_100g")
public void setNutritionScoreUk100g(String nutritionScoreUk100g) {
this.nutritionScoreUk100g = nutritionScoreUk100g;
}

public OpenProduct withNutritionScoreUk100g(String nutritionScoreUk100g) {
this.nutritionScoreUk100g = nutritionScoreUk100g;
return this;
}

@JsonProperty("glycemic-index_100g")
public String getGlycemicIndex100g() {
return glycemicIndex100g;
}

@JsonProperty("glycemic-index_100g")
public void setGlycemicIndex100g(String glycemicIndex100g) {
this.glycemicIndex100g = glycemicIndex100g;
}

public OpenProduct withGlycemicIndex100g(String glycemicIndex100g) {
this.glycemicIndex100g = glycemicIndex100g;
return this;
}

@JsonProperty("water-hardness_100g")
public String getWaterHardness100g() {
return waterHardness100g;
}

@JsonProperty("water-hardness_100g")
public void setWaterHardness100g(String waterHardness100g) {
this.waterHardness100g = waterHardness100g;
}

public OpenProduct withWaterHardness100g(String waterHardness100g) {
this.waterHardness100g = waterHardness100g;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public OpenProduct withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}