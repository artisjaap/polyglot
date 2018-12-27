import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HomeComponent} from './home/home.component';
import {LoginRegisterFlipcardComponent} from './login-register-flipcard/login-register-flipcard.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent, children: [

      {path: 'login', component: LoginRegisterFlipcardComponent},
      {path: 'user', loadChildren: '../user/user.module#UserModule'},
      {path: 'admin', loadChildren: '../admin/admin.module#AdminModule'},
      {path: 'student', loadChildren: '../student/student.module#StudentModule'},
      {path: 'teacher', loadChildren: '../teacher/teacher.module#TeacherModule'},

    ]},

  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class CoreRoutingModule {
}
