import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class UserInfoService {

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getUserInfo(id: string) {
        return this.apiService.get(this.config.userInfo_url + '/' + id);
    }

    getMyUserInfo() {
        return this.apiService.get(this.config.userInfoMy_url);
    }

    saveUserInfo(info) {
        return this.apiService.post(this.config.userInfoSave_url, info);
    }

    getUserAddress(id: string) {
        return this.apiService.get(this.config.userAddress_url + '/' + id);
    }

    getMyUserAddress() {
        return this.apiService.get(this.config.userAddressMy_url );
    }

    saveUserAddress(address) {
        return this.apiService.post(this.config.userAddressSave_url, address);
    }

    updateUserAddress(address) {
        return this.apiService.post(this.config.userAddressUpdate_url, address);
    }


}