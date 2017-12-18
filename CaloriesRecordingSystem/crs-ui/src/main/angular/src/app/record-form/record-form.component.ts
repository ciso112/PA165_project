import { Component, OnInit } from '@angular/core';
import {Record} from '../_classes/Record';
import {RecordService} from '../_services/record.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ActivityService} from '../_services/activity.service';
import {IActivity} from '../_interfaces/IActivity';

@Component({
  selector: 'app-record-form',
  templateUrl: './record-form.component.html',
  styleUrls: ['./record-form.component.css']
})
export class RecordFormComponent implements OnInit {

  record = new Record();
  activities: IActivity[];

  constructor(
    private route: ActivatedRoute,
    private recordService: RecordService,
    private activityService: ActivityService,
    private router: Router,
  ) { }

  getAllActivities() {
    this.activityService
      .getAllActivities()
      .subscribe(activities => {
        this.activities = activities;
        if (activities) {
          this.record.activityId = activities[0].id;
        }
      });
  }

  createNewRecord() {
    this.recordService
      .createNewRecord(this.record)
      .subscribe(newRecordId => {
        if (!newRecordId) {

        } else {
          this.router.navigateByUrl('records/' + newRecordId);
        }
      });
  }

  getRecord() {
    const routeId = this.route.snapshot.paramMap.get('id');

    if (routeId) {
      const id = +routeId;
      this.recordService
        .getRecordForUpdate(id)
        .subscribe(record => this.record = record);
    }
  }

  ngOnInit() {
    this.getRecord();
    this.getAllActivities();
  }

}
