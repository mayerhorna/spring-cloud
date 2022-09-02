import {Component} from '@angular/core';
import {AppService} from './app.service'
 
@Component({
    selector: 'home-header',
    providers: [AppService],
	templateUrl: './home.component.html'
})
 
export class HomeComponent {
    public isLoggedIn = false;

    constructor(
        private _service:AppService){}
    
    ngOnInit(){
        this.isLoggedIn = this._service.isLoggedIn();
    }

    login() {
        this._service.obtainAccessToken();
    }

    logout() {
        this._service.logout();
    }
}