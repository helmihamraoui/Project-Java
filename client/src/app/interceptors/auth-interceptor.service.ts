// src/app/interceptors/auth-interceptor.service.ts
import { HttpInterceptorFn, HttpRequest, HttpHandler } from '@angular/common/http';

// Define the token interceptor function
export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  console.log('Token:', token);
  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  return next(req);
};
