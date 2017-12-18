import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UserSettings} from '../_classes/UserSettings';
import {IUserShow} from "../_interfaces/IUserShow";

const prefix = '/users/';

const usersShow = prefix + 'show';
const updateSettings = prefix + 'update';
const allUsers = prefix + 'allUsers';


@Injectable()
export class UserService {
  constructor(
    private http: HttpClient,
  ) {}

  getAllUsersShow(): Observable<IUserShow[]> {
    return this.http
      .get<IUserShow[]>(usersShow);
  }

  saveUserSettings(newSettings: UserSettings): Observable<IUserShow> {
    return this.http
      .post<IUserShow>(updateSettings, newSettings);
  }
}
