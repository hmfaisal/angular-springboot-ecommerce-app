import { Component,Input,OnInit,OnDestroy} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-my-request',
  templateUrl: './my-request.component.html',
  styleUrls: ['./my-request.component.scss']
})
export class MyRequestComponent implements OnInit{

  @Input() menuitem: any;
  private subscription = new Subscription();
  tabs = [];
  active:string;
  delivered:string;
  archived:string;
  completed:string;
  selectedTab:any;
  selectedIndex: number;

  constructor(
    private translate: TranslateService
  ) {
    this.subscription.add(translate.get('ACTIVE').subscribe((res: string) => {
      this.active = res;
    }));
    this.subscription.add(translate.get('DELIVERED').subscribe((result: string) => {
      this.delivered = result;
    }));
    this.subscription.add(translate.get('ARCHIVED').subscribe((result: string) => {
      this.archived = result;
    }));
    this.subscription.add(translate.get('COMPLETED').subscribe((result: string) => {
      this.completed = result;
    }));
  }

  ngOnInit(){
    this.tabs = [
      { id:"tab1",label: this.completed },
      { id:"tab2",label: this.archived},
      { id:"tab3",label: this.delivered},
      { id:"tab4",label: this.active },
    ];
    this.selectedTab="tab4";
  }

  triggerTab(tab, index: number) {
    this.selectedTab = tab.id;
    this.selectedIndex = index;
  }

}
