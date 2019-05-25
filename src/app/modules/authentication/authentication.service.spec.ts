import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


const testData = {
  username: "test",
  password: "testpass",
  email: "testemail"
};

describe('AuthenticationService', () => {
  let authService: AuthenticationService;
  let httpTestingController: HttpTestingController;
  const registerEndpoint = "http://localhost:8086/usertrackservice/api/v1/usertrackservice/register";
  const loginEndPoint = "http://localhost:8086/authenticationservice/api/v1/userservice/login";
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthenticationService]

    });
    authService = TestBed.get(AuthenticationService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    // const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(authService).toBeTruthy();
  });

  it("#registerUser() user should get proper response from httpCall", () => {
    authService.registerUser(testData).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testData);
    });

    const httpMockReq = httpTestingController.expectOne(registerEndpoint);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(registerEndpoint);
  });

  it("#login() should get proper response from httpCal", () => {
    authService.loginUser(testData).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testData);
    });

    const httpMockReq = httpTestingController.expectOne(loginEndPoint);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(loginEndPoint);
  });




});