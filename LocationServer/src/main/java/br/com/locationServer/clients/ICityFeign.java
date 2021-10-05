package br.com.locationServer.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "http://location-server/city")
public interface ICityFeign {

}