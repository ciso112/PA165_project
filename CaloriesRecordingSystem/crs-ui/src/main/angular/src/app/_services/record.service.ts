import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IRecord} from '../_interfaces/IRecord';
import {Observable} from 'rxjs/Observable';
import {IRecordDetail} from '../_interfaces/IRecordDetail';
import {Record} from "../_classes/Record";

const prefix = '/records/';

const allRecords = prefix;
const recordDetail = prefix;
const createRecord = prefix + 'create';

const updateRecord = prefix;
const deleteRecord = prefix;
const progress = prefix + 'progress';


@Injectable()
export class RecordService {
  constructor(
    private http: HttpClient,
  ) {}

  getAllRecordsOfUser(): Observable<IRecord[]> {
    return this.http
      .get<IRecord[]>(allRecords);
  }

  getRecordDetail(recordId: number): Observable<IRecordDetail> {
    return this.http
      .get<IRecordDetail>(recordDetail + recordId);
  }

  createNewRecord(record: Record): Observable<IRecordDetail> {
    return this.http
      .post<IRecordDetail>(createRecord, { record });

  }

  updateRecord(record: IRecordDetail): Observable<IRecordDetail> {
    return this.http
      .post<IRecordDetail>(updateRecord, {record});
  }

  getWeekProgressOfBurnedCalories(): Observable<number> {
    return this.http
      .get<number>(progress);
  }
}
