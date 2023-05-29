package com.darkstore.transfer.client.depot;

import com.darkstore.transfer.client.depot.model.dto.stock.info.StockInfoRequestDto;
import com.darkstore.transfer.client.depot.model.dto.stock.info.StockInfoResponseDto;
import com.darkstore.transfer.client.depot.model.dto.stock.info.StockInfoRestResponseDto;
import com.darkstore.transfer.client.depot.model.dto.stock.update.UpdateStockRequestDto;
import com.darkstore.transfer.client.depot.model.dto.stock.update.UpdateStockRestResponseDto;
import com.darkstore.transfer.common.exception.RestException;
import com.darkstore.transfer.common.response.model.RestResponseHeader;
import com.darkstore.transfer.model.response.TransferRestResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.darkstore.transfer.client.depot.model.Properties.*;

@Service
@Slf4j
public class DepotClient {
    private final RestTemplate restTemplate;

    @Autowired
    public DepotClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StockInfoResponseDto stockInfo(StockInfoRequestDto requestDto) {
        String logErrorMessage = "method:stockInfo details: ";
        try {
            ResponseEntity<StockInfoRestResponseDto> response = restTemplate.postForEntity(BASE_URL + STOCK_INFO, requestDto, StockInfoRestResponseDto.class);
            StockInfoRestResponseDto body = response.getBody();
            if (body == null)
                throw new RestException(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR);
            checkResponse(response.getStatusCode(), body.getHeader());
            return body.getDetail();
        } catch (RestException restException) {
            restException.setTranslator(false);
            log.error(logErrorMessage, restException);
            throw restException;
        } catch (Exception e) {
            log.error(logErrorMessage, e);
            throw new RestException(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR);
        }
    }

    public void updateStock(UpdateStockRequestDto requestDto) {
        String logErrorMessage = "method:updateStock details: ";
        try {
            ResponseEntity<UpdateStockRestResponseDto> response = restTemplate.postForEntity(BASE_URL + UPDATE_STOCK, requestDto, UpdateStockRestResponseDto.class);
            UpdateStockRestResponseDto body = response.getBody();
            if (body == null)
                throw new RestException(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR);
            checkResponse(response.getStatusCode(), body.getHeader());
        } catch (RestException restException) {
            restException.setTranslator(false);
            log.error(logErrorMessage, restException);
            throw restException;
        } catch (Exception e) {
            log.error(logErrorMessage, e);
            throw new RestException(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR);
        }
    }

    private static void checkResponse(HttpStatusCode statusCode, RestResponseHeader header) {
        if (header == null)
            throw new RestException(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR);
        if (statusCode.isError() || !SUCCESS.equals(header.getResponseCode()))
            throw new RestException(new TransferRestResponseCode(TransferRestResponseCode.TRN_DEPOT_CLIENT_ERROR.getResponseCode(),
                    header.getResponseMessage()));
    }
}
