import { Component } from '@angular/core';
import {AppService} from './app.service'
import {Sentence} from './sentence-model'

@Component({ 
  selector: 'sentence-details',
  providers: [AppService],  
  templateUrl: './sentence.component.html'
})

export class SentenceComponent {
    public sentence = new Sentence(0,'');
	private servicesUrl = 'http://localhost:8090/apisentence'; 
    constructor(private _service:AppService) {}
	
    getSentence(){
        this._service.getResource(this.servicesUrl + '/get')  
         .subscribe(
                     data => this.sentence.text = data.text,
                     error =>  this.sentence.text = 'Error'); 	 					 
    }
}