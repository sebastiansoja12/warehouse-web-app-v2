import {User} from './user';
import {Depot} from './depot';
import {Parcel} from './parcel';

export class Route {
  id: string;
  created: Date;
  user: User;
  depot: Depot;
  parcel: Parcel;

}
