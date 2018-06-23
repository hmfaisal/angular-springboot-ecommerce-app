import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable()
export class ConfigService {

    private _api_url = '/api';
    private _refresh_token_url = this._api_url + '/refresh';
    private _login_url = this._api_url + '/login';
    private _logout_url = this._api_url + '/logout';
    private _register_url = this._api_url + '/register';
    private _facebookLogin_url =  '/auth/facebook';
    private _change_password_url = this._api_url + '/changePassword';
    private _whoami_url = this._api_url + '/whoami';
    private _user_url = this._api_url + '/user';
    private _reset_credentials_url = this._user_url + '/reset-credentials';
    
    private _product_url = this._api_url + '/product';
    private _allproduct_url = this._product_url + '/all';
    private _allproductCount_url = this._product_url + '/all/count';
    private _productByName_url = this._product_url + '/name';
    private _productByNameCount_url = this._product_url + '/name/count';
    private _productByCountry_url = this._product_url + '/country';
    private _productByShop_url = this._product_url + '/shop';
    private _productByShopCount_url = this._product_url + '/shop/count';
    private _productByRange_url = this._product_url + '/range';
    private _productByRangeCount_url = this._product_url + '/range/count';
    private _productByLow_url = this._product_url + '/low';
    private _productByHigh_url = this._product_url + '/high';
    private _productByCategory_url = this._product_url + '/category';
    private _productByCategoryCount_url = this._product_url + '/category/count';
    private _productCategory_url = this._product_url + '/category/all';
    private _productBrand_url = this._product_url + '/brand/all';

    private _list_url = this._api_url + '/list';
    private _listByName_url = this._list_url + '/name';
    private _mylist_url = this._list_url + '/my';
    private _mylistCount_url = this._list_url + '/my/count';
    private _listSave_url = this._list_url + '/save';
    private _listUpdate_url = this._list_url + '/update';
    private _listRemove_url = this._list_url + '/remove';

    private _listProduct_url = this._list_url + '/product';
    private _listProductCount_url = this._list_url + '/product/count';
    private _listProductUnlisted_url = this._listProduct_url + '/unlisted';
    private _listProductUnlistedCount_url = this._listProduct_url + '/unlisted/count';
    private _listProductSave_url = this._listProduct_url + '/save';
    private _listProductUpdate_url = this._listProduct_url + '/update';
    private _listProductRemove_url = this._listProduct_url + '/remove';
    
    private _request_url = this._api_url + '/request';
    private _myAllRequest_url = this._request_url + '/my/all';
    private _myActiveRequest_url = this._request_url + '/my/active';
    private _myArchivedRequest_url = this._request_url + '/my/archived';
    private _myDeliveredRequest_url = this._request_url + '/my/delivered';
    private _myConfirmedRequest_url = this._request_url + '/my/confirmed';
    private _myActiveRequestCount_url = this._request_url + '/my/active/count';
    private _myArchivedRequestCount_url = this._request_url + '/my/archived/count';
    private _myDeliveredRequestCount_url = this._request_url + '/my/delivered/count';
    private _myConfirmedRequestCount_url = this._request_url + '/my/confirmed/count';
    private _requestActive_url = this._request_url + '/active';
    private _requestActiveAll_url = this._request_url + '/active/all';
	private _requestActiveCount_url = this._request_url + '/active/count';
    private _requestCurrency_url = this._request_url + '/currency';
    private _requestByTime_url = this._request_url + '/by/time';
    private _requestByTimeCount_url = this._request_url + '/by/time/count';
    private _requestByDistance_url = this._request_url + '/by/distance';
    private _requestByRange_url = this._request_url + '/by/range';
    private _requestByRangeCount_url = this._request_url + '/by/range/count';
    private _requestSave_url = this._request_url + '/save';
    private _requestUpdate_url = this._request_url + '/update';
    private _requestRemove_url = this._request_url + '/remove';
    private _requestConfirm_url = this._request_url + '/confirm/save';

    private _deliveryAddress_url = this._api_url + '/delivery/address';
    private _deliveryAddressUpdate_url = this._deliveryAddress_url + '/save';

    private _accept_url = this._api_url + '/accept';
    private _myActiveAccept_url = this._accept_url + '/my/active';
    private _myArchivedAccept_url = this._accept_url + '/my/archived';
    private _myActiveAcceptCount_url = this._accept_url + '/my/active/count';
    private _myArchivedAcceptCount_url = this._accept_url + '/my/archived/count';
    private _acceptSave_url = this._accept_url + '/save';
    private _acceptDeliver_url = this._accept_url + '/deliver/save';
    private _acceptRemove_url = this._accept_url + '/remove';

    private _shoppingProduct_url = this._api_url + '/request/product';
    private _shoppingProductSave_url = this._shoppingProduct_url + '/save';
    private _shoppingProductUpdate_url = this._shoppingProduct_url + '/update';
    private _shoppingProductRemove_url = this._shoppingProduct_url + '/remove';

    private _userInfo_url = this._api_url + '/userinfo';
    private _userInfoSave_url = this._userInfo_url + '/save';
    private _userInfoMy_url = this._userInfo_url + '/my';

    private _userAddress_url = this._api_url + '/user/address';
    private _userAddressSave_url = this._userAddress_url + '/save';
    private _userAddressUpdate_url = this._userAddress_url + '/update';
    private _userAddressMy_url = this._userAddress_url + '/my';

    get reset_credentials_url(): string {
        return this._reset_credentials_url;
    }

    get refresh_token_url(): string {
        return this._refresh_token_url;
    }

    get whoami_url(): string {
        return this._whoami_url;
    }

    get user_url(): string {
        return this._user_url;
    }

    get login_url(): string {
        return this._login_url;
    }

    get logout_url(): string {
        return this._logout_url;
    }

    get register_url(): string {
        return this._register_url;
    }

    get facebookLogin_url(): string {
        return this._facebookLogin_url;
    }

    get change_password_url(): string {
        return this._change_password_url;
    }



    get product_url(): string {
        return this._product_url;
    }

    get allproduct_url(): string {
        return this._allproduct_url;
    }

    get allproductCount_url(): string {
        return this._allproductCount_url;
    }

    get productByName_url(): string {
        return this._productByName_url;
    }

    get productByNameCount_url(): string {
        return this._productByNameCount_url;
    }

    get productByCountry_url(): string {
        return this._productByCountry_url;
    }

    get productByShop_url(): string {
        return this._productByShop_url;
    }

    get productByShopCount_url(): string {
        return this._productByShopCount_url;
    }

    get productByLow_url(): string {
        return this._productByLow_url;
    }

    get productByHigh_url(): string {
        return this._productByHigh_url;
    }

    get productByCategory_url(): string {
        return this._productByCategory_url;
    }

    get productByCategoryCount_url(): string {
        return this._productByCategoryCount_url;
    }

    get productByRange_url(): string {
        return this._productByRange_url;
    }

    get productByRangeCount_url(): string {
        return this._productByRangeCount_url;
    }

    get productCategory_url(): string {
        return this._productCategory_url;
    }

    get productBrand_url(): string {
        return this._productBrand_url;
    }



    get list_url(): string {
        return this._list_url;
    }

    get listByName_url(): string {
        return this._listByName_url;
    }

    get mylist_url(): string {
        return this._mylist_url;
    }

    get mylistCount_url(): string {
        return this._mylistCount_url;
    }
    
    get listSave_url(): any {
        return this._listSave_url;
    }

    get listUpdate_url(): any {
        return this._listUpdate_url;
    }

    get listRemove_url(): string {
        return this._listRemove_url;
    }



    get listProduct_url(): string {
        return this._listProduct_url;
    }

    get listProductCount_url(): string {
        return this._listProductCount_url;
    }

    get listProductUnlisted_url(): string {
        return this._listProductUnlisted_url;
    }

    get listProductUnlistedCount_url(): string {
        return this._listProductUnlistedCount_url;
    }

    get listProductSave_url(): any {
        return this._listProductSave_url;
    }

    get listProductUpdate_url(): any {
        return this._listProductUpdate_url;
    }

    get listProductRemove_url(): any {
        return this._listProductRemove_url;
    }


    get deliveryAddress_url(): any {
        return this._deliveryAddress_url;
    }

    get deliveryAddressUpdate_url(): any {
        return this._deliveryAddressUpdate_url;
    }



    get request_url(): string {
        return this._request_url;
    }

    get myAllRequest_url(): string {
        return this._myAllRequest_url;
    }

    get myActiveRequest_url(): string {
        return this._myActiveRequest_url;
    }

    get myActiveRequestCount_url(): string {
        return this._myActiveRequestCount_url;
    }

    get myArchivedRequest_url(): string {
        return this._myArchivedRequest_url;
    }

    get myArchivedRequestCount_url(): string {
        return this._myArchivedRequestCount_url;
    }

    get myConfirmedRequest_url(): string {
        return this._myConfirmedRequest_url;
    }

    get myConfirmedRequestCount_url(): string {
        return this._myConfirmedRequestCount_url;
    }

    get myDeliveredRequest_url(): string {
        return this._myDeliveredRequest_url;
    }

    get myDeliveredRequestCount_url(): string {
        return this._myDeliveredRequestCount_url;
    }

    get requestActive_url(): string {
        return this._requestActive_url;
    }

    get requestActiveCount_url(): string {
        return this._requestActiveCount_url;
    }

    get requestActiveAll_url(): string {
        return this._requestActiveAll_url;
    }

    get requestByTime_url(): string {
        return this._requestByTime_url;
    }

    get requestByTimeCount_url(): string {
        return this._requestByTimeCount_url;
    }

    get requestByDistance_url(): string {
        return this._requestByDistance_url;
    }

    get requestByRange_url(): string {
        return this._requestByRange_url;
    }

    get requestByRangeCount_url(): string {
        return this._requestByRangeCount_url;
    }

    get requestSave_url(): string {
        return this._requestSave_url;
    }

    get requestUpdate_url(): string {
        return this._requestUpdate_url;
    }

    get requestRemove_url(): string {
        return this._requestRemove_url;
    }

    get requestConfirm_url(): string {
        return this._requestConfirm_url;
    }

    get requestCurrency_url(): string {
        return this._requestCurrency_url;
    }


    get accept_url(): string {
        return this._accept_url;
    }

    get myActiveAccept_url(): string {
        return this._myActiveAccept_url;
    }

    get myActiveAcceptCount_url(): string {
        return this._myActiveAcceptCount_url;
    }

    get myArchivedAccept_url(): string {
        return this._myArchivedAccept_url;
    }

    get myArchivedAcceptCount_url(): string {
        return this._myArchivedAcceptCount_url;
    }

    get acceptSave_url(): string {
        return this._acceptSave_url;
    }

    get acceptDeliver_url(): string {
        return this._acceptDeliver_url;
    }

    get acceptRemove_url(): string {
        return this._acceptRemove_url;
    }


    get shoppingProduct_url(): string {
        return this._shoppingProduct_url;
    }

    get shoppingProductSave_url(): string {
        return this._shoppingProductSave_url;
    }

    get shoppingProductUpdate_url(): string {
        return this._shoppingProductUpdate_url;
    }

    get shoppingProductRemove_url(): string {
        return this._shoppingProductRemove_url;
    }

    get userInfo_url(): string {
        return this._userInfo_url;
    }

    get userInfoSave_url(): string {
        return this._userInfoSave_url;
    }

    get userInfoMy_url(): string {
        return this._userInfoMy_url;
    }

    get userAddress_url(): string {
        return this._userAddress_url;
    }

    get userAddressSave_url(): string {
        return this._userAddressSave_url;
    }

    get userAddressUpdate_url(): string {
        return this._userAddressUpdate_url;
    }

    get userAddressMy_url(): string {
        return this._userAddressMy_url;
    }

    



}
