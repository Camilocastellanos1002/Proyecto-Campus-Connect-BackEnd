package com.campusConnect.CampusConnect.api.dto.response.fuertes.intermedias;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaBasicResp {
    private Long idAgenda;
    private Date evenTitle;
    private Date evenStartDate;
    private Date evenEndDate;
    private Time evenStartTime;
    private Time evenEndTime;
    private String evenLocation;
    private String evenURL;
}
