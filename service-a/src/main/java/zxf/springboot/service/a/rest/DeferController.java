package zxf.springboot.service.a.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import zxf.springboot.support.rest.ServerResponse;
import zxf.springboot.support.trace.RunWIthTraceId;

import java.util.concurrent.ForkJoinPool;

@Slf4j
@RestController
@RequestMapping("/defers")
public class DeferController {

    @GetMapping("/success")
    public DeferredResult<ServerResponse<String>> success() {
        log.info("DeferController::success start");
        DeferredResult<ServerResponse<String>> output = new DeferredResult<>(2000L);
        output.onCompletion(RunWIthTraceId.wrap(() -> {
            log.info("DeferController::success onCompletion");
        }));
        ForkJoinPool.commonPool().submit(RunWIthTraceId.wrap(() -> {
            log.info("DeferController::success defer start");
            output.setResult(ServerResponse.success("OK"));
            log.info("DeferController::success defer end");
        }));
        log.info("DeferController::success end");
        return output;
    }

    @GetMapping("/timeout")
    public DeferredResult<ServerResponse<String>> timeout() {
        log.info("DeferController::timeout start");
        DeferredResult<ServerResponse<String>> output = new DeferredResult<>(2000L);
        output.onTimeout(RunWIthTraceId.wrap(() -> {
            log.info("DeferController::timeout onTimeout");
        }));
        ForkJoinPool.commonPool().submit(RunWIthTraceId.wrap(() -> {
            log.info("DeferController::timeout defer start");
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                output.setErrorResult(e);
            }
            output.setResult(ServerResponse.success("Timeout"));
            log.info("DeferController::timeout defer end");
        }));
        log.info("DeferController::timeout end");
        return output;
    }

    @GetMapping("/error")
    public DeferredResult<ServerResponse<String>> error() {
        log.info("DeferController::error start");
        DeferredResult<ServerResponse<String>> output = new DeferredResult<>(2000L);
        output.onError(RunWIthTraceId.wrap((throwable) -> {
            log.error("DeferController::timeout onError", throwable);
        }));
        ForkJoinPool.commonPool().submit(RunWIthTraceId.wrap(() -> {
            log.info("DeferController::error defer start");
            output.setErrorResult(new IllegalAccessException("error for defer"));
        }));
        log.info("DeferController::error end");
        return output;
    }
}
