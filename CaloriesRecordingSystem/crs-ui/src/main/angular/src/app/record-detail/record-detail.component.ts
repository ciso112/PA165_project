import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RecordService} from '../_services/record.service';
import {IRecordDetail} from '../_interfaces/IRecordDetail';

@Component({
  selector: 'app-record-detail',
  templateUrl: './record-detail.component.html',
  styleUrls: ['./record-detail.component.css']
})
export class RecordDetailComponent implements OnInit {

  record: IRecordDetail;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private recordService: RecordService,
  ) { }

  ngOnInit(): void {
    this.getRecord();
  }

  getRecord(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.recordService
      .getRecordDetail(this.id)
      .subscribe(record => this.record = record);
  }

}
