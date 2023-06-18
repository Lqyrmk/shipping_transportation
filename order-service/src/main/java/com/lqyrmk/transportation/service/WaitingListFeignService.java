package com.lqyrmk.transportation.service;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/18 11:05
 */
@Component
@FeignClient("WAITLIST-SERVICE")
public interface WaitingListFeignService {

    @GetMapping("/waitingList/")
    Result<WaitingList> getWaitingList();

    @PostMapping("/waitingList/")
    Result<WaitingList> addToList();

}
