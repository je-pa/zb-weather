package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @Operation(summary = "날씨일기저장" , description = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/diary")
    Diary createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            , @RequestBody String text){
        return diaryService.createDiary(date, text);
    }

    @Operation(summary = "날씨 일기 조회", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/diary")
    List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ){
        return diaryService.readDiary(date);
    }
    @Operation(summary = "날씨 일기 기간 조회", description = "선택한 기간 중의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 시작 날짜", example = "2024-01-01") LocalDate startDate
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 마지막 날짜", example = "2023-01-30") LocalDate endDate
    ){
        return diaryService.readDiaries(startDate, endDate);
    }
    @Operation(summary = "날씨 일기 수정", description = "해당 날짜의 일기 중 첫번째를 수정합니다.")
    @PutMapping("/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            , @RequestBody String text
    ){
        diaryService.updateDiary(date,text);
    }
    @Operation(summary = "날씨 일기 삭제", description = "해당 날짜의 일기 삭제 합니다.")
    @DeleteMapping("/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ){
        diaryService.deleteDiary(date);
    }
}
