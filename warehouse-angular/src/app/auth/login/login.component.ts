import {Component, HostListener, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoginRequestPayload } from './login-request.payload';
import { AuthService } from '../service/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isCollapsed = true;
  focus;
  focus1;
  focus2;
  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  registerSuccessMessage: string;
  isError: boolean;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute,
              private router: Router, private toastr: ToastrService) {
    this.loginRequestPayload = {
      username: '',
      password: ''
    };
  }

    @HostListener('document:mousemove', ['$event'])
    // tslint:disable-next-line:typedef
    onMouseMove(e)
    {
      const squares1 = document.getElementById('square1');
      const squares2 = document.getElementById('square2');
      const squares3 = document.getElementById('square3');
      const squares4 = document.getElementById('square4');
      const squares5 = document.getElementById('square5');
      const squares6 = document.getElementById('square6');
      const squares7 = document.getElementById('square7');
      const squares8 = document.getElementById('square8');

      const posX = e.clientX - window.innerWidth / 2;
      const posY = e.clientY - window.innerWidth / 6;

      squares1.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares2.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares3.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares4.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares5.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares6.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.05 +
        'deg) rotateX(' +
        posY * -0.05 +
        'deg)';
      squares7.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.02 +
        'deg) rotateX(' +
        posY * -0.02 +
        'deg)';
      squares8.style.transform =
        'perspective(500px) rotateY(' +
        posX * 0.02 +
        'deg) rotateX(' +
        posY * -0.02 +
        'deg)';
    }

    ngOnInit(): any
    {
      this.loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      });

      this.activatedRoute.queryParams
        .subscribe(params => {
          if (params.registered !== undefined && params.registered === 'true') {
            this.toastr.success('Użytkownik zarejestrowany');
          }
        });

      const body = document.getElementsByTagName('body')[0];
      body.classList.add('register-page');

      this.onMouseMove(event);
    }

    login(): any
    {
      this.loginRequestPayload.username = this.loginForm.get('username').value;
      this.loginRequestPayload.password = this.loginForm.get('password').value;

      this.authService.login(this.loginRequestPayload).subscribe(data => {
        this.isError = false;
        if (this.authService.isAdmin() === true) {
          window.location.assign('/admin/routes/all/inparcel-admin');
        } else {
          window.location.assign('/');
        }
        this.toastr.success('Logowanie powiodło się');

      }, error => {
        this.isError = true;
        throwError(error);
      });


    }
      ngOnDestroy(): any {
      const body = document.getElementsByTagName('body')[0];
      body.classList.remove('register-page');
    }
  }

