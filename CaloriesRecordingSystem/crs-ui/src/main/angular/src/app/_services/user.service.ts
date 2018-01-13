import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {IUserDetail} from "../_interfaces/IUserDetail";
import {IRecord} from "../_interfaces/IRecord";

const prefix = '/users/';

const allUsers = prefix;
const userRecords = '/records';


@Injectable()
export class UserService {
  constructor(
    private http: HttpClient,
  ) {}

  getAllUsers(): Observable<IUserDetail[]> {
    return this.http
      .get<IUserDetail[]>(allUsers);
  }

  getUserRecords(userId: number): Observable<IRecord[]> {
    return this.http
      .get<IRecord[]>(prefix + userId + userRecords);
  }


}
