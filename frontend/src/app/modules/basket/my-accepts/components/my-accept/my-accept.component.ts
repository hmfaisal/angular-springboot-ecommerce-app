import { Component,Input,OnInit,OnDestroy} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-my-accept',
  templateUrl: './my-accept.component.html',
  styleUrls: ['./my-accept.component.scss']
})
export class MyAcceptComponent implements OnInit{

  @Input() menuitem: any;
  private subscription = new Subscription();
  tabs = [];
  active:string;
  archived:string;
  selectedTab:any;
  selectedIndex: number;

  constructor(
    private translate: TranslateService
  ) {
    this.subscription.add(translate.get('ACTIVE').subscribe((res: string) => {
      this.active = res;
    }));
    this.subscription.add(translate.get('ARCHIVED').subscribe((result: string) => {
      this.archived = result;
    }));
  }

  ngOnInit(){
    this.tabs = [
      { id:"tab1", label: this.archived },
      { id:"tab2", label: this.active},
    ];
    this.selectedTab="tab2";
  }

  triggerTab(tab, index: number) {
    this.selectedTab = tab.id;
    this.selectedIndex = index;
  }

}
