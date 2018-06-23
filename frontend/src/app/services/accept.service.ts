import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class AcceptService {

    params: any;
    
    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getAccept(id: string) {
        return this.apiService.get(this.config.accept_url + '/' + id);
    }

    getMyActiveAccept(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myActiveAccept_url, this.params);
    }

    getMyActiveAcceptCount() {
        return this.apiService.get(this.config.myActiveAcceptCount_url);
    }

    getMyArchivedAccept(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myArchivedAccept_url, this.params);
    }

    getMyArchivedAcceptCount() {
        return this.apiService.get(this.config.myArchivedAcceptCount_url);
    }

    saveAccept(request) {
        return this.apiService.post(this.config.acceptSave_url, request);
    }

    removeAccept(request) {
        return this.apiService.post(this.config.acceptRemove_url, request);
    }

    deliverAccept(request) {
        return this.apiService.post(this.config.acceptDeliver_url, request);
    }


}