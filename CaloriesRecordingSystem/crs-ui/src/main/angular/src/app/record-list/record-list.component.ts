import {Component, OnInit} from '@angular/core';
import {RecordService} from '../_services/record.service';
import {IRecord} from '../_interfaces/IRecord';

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.css']
})
export class RecordListComponent implements OnInit {

  userRecordsCached: IRecord[];
  userRecords: IRecord[];
  progress: string;

  constructor(private recordService: RecordService) {
  }

  loadAllUserRecordsFromServer() {
    this.recordService
      .getAllRecordsOfUser()
      .subscribe(userRecords => {
        this.userRecordsCached = userRecords;
        this.getAllUserRecords();
      });
  }

  getAllUserRecords() {
    this.userRecords = this.userRecordsCached;
  }

  getWeekProgressOfBurnedCalories() {
    this.recordService.getWeekProgressOfBurnedCalories()
      .subscribe(progress => this.progress = progress.toString() + '%');
  }

  ngOnInit() {
    this.loadAllUserRecordsFromServer();
    this.getWeekProgressOfBurnedCalories();
  }

}
