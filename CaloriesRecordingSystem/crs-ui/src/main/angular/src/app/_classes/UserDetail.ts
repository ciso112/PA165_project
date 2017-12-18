import {UserSettings} from './UserSettings';
import {LoginCredentials} from './LoginCredentials';

export class UserDetail extends UserSettings {
  id: number;
  birthDate: string;
  isMale: boolean;
  isAdmin: boolean;
}
