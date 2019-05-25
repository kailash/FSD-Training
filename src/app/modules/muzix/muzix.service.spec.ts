import { TestBed } from '@angular/core/testing';

import { MuzixService } from './muzix.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Track } from './track';

describe('MuzixService', () => {

  let track = new Track();
  track = {
    trackId: "234",
    name: "abc",
    listeners: "xyz",
    url: "http:url",
    comments: "avx xyz",
    artist: {
      artisId: 123,
      name: "arname",
      url: "htto://url",
      image: {
        imageId: 987,
        text: "aaa",
        size: "23"
      }
    }
  };

  const springEndpoint = "http://localhost:8086/usertrackservice/api/v1/usertrackservice/";
  let muzixService: MuzixService;
  let httpTestingController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MuzixService]
    });
    muzixService = TestBed.get(MuzixService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(muzixService).toBeTruthy();
  });

  it("#addToWishList should return proper http response", () => {
    muzixService.addToWishList(track).subscribe(res=>{
      expect(res.body).toBe(track);
    });

    const url = springEndpoint + "user/test/track";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it("#getAllTracksForWishList should return proper http response", () => {
    muzixService.getAllTracksForWishList().subscribe(res=>{
      //expect(res.body).toBe(track);
    });

    const url = springEndpoint + "user/test/tracks";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it("#deleteTrackfromWishList should return proper http response", () => {
    muzixService.deleteTrackfromWishList(track).subscribe(res=>{
      //expect(res.body).toBe(track);
    });
    const url = springEndpoint + "user/test/"+track.trackId;
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it("#updateComment should return proper http response", () => {
    muzixService.updateComment(track).subscribe(res=>{
      expect(res.body).toBe(track);
    });
    const url = springEndpoint + "user/test/track";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('PATCH');
    expect(httpMockReq.request.url).toEqual(url);
  });


});
