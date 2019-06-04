import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HomeComponent} from './home/home.component';
import {LoginRegisterFlipcardComponent} from './login-register-flipcard/login-register-flipcard.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent, children: [

      {path: 'login', component: LoginRegisterFlipcardComponent},
      {path: 'user', loadChildren: () => import('../user/user.module').then(m => m.UserModule)},
      {path: 'admin', loadChildren: () => import('../admin/admin.module').then(m => m.AdminModule)},
      {path: 'student', loadChildren: () => import('../student/student.module').then(m => m.StudentModule)},
      {path: 'teacher', loadChildren: () => import('../teacher/teacher.module').then(m => m.TeacherModule)},

    ]},

  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class CoreRoutingModule {
}
