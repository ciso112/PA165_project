import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {IUserDetail} from "../_interfaces/IUserDetail";

const prefix = '/users/';

const allUsers = prefix;


@Injectable()
export class UserService {
  constructor(
    private http: HttpClient,
  ) {}

  getAllUsers(): Observable<IUserDetail[]> {
    return this.http
      .get<IUserDetail[]>(allUsers);
  }


}
