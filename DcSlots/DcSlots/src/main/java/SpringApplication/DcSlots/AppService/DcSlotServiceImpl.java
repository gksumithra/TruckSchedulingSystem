package SpringApplication.DcSlots.AppService;

import SpringApplication.DcSlots.AppModel.DcSlots;
import SpringApplication.DcSlots.AppModel.TimeSlot;
import SpringApplication.DcSlots.AppRepository.DcSlotRepository;
import SpringApplication.DcSlots.exception.DcSlotAlreadyExistsException;
import SpringApplication.DcSlots.exception.DcSlotNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
@Service
@Slf4j

public class DcSlotServiceImpl implements IdcSlotService {
    @Autowired
    DcSlotRepository dcSlotRespository;
    @Autowired
    private TimeSlot timeSlot;

    @Override
    public DcSlots createDcSlots(@RequestBody DcSlots dcslots) throws DcSlotAlreadyExistsException
    {

        if(dcSlotRespository.findById(dcslots.getDcNumber()).isPresent())
        {


            throw new DcSlotAlreadyExistsException("DcSlot Already Exists for dcNumber:" +dcslots.getDcNumber());
        }
        dcslots.setDcTimeSlots(timeSlot.dcSlotMethod(Integer.parseInt(dcslots.getDcTimeSlots()),0));
        return dcSlotRespository.save(dcslots);    }

    @Override
    public ResponseEntity<DcSlots> getDcSlotById(Long dcNumber) throws DcSlotNotFoundException {
        DcSlots dcslots = dcSlotRespository.findById(dcNumber)
                .orElseThrow(() -> new DcSlotNotFoundException("DcSlot not found for this id :: " + dcNumber));
        return ResponseEntity.ok().body(dcslots);

    }

    @Override
    public DcSlots updateDcSlot(Long dcNumber, DcSlots dcslot) throws DcSlotNotFoundException {
        if(dcSlotRespository.findById(dcNumber).isPresent()) {

            dcslot.setDcNumber(dcslot.getDcNumber());
            dcslot.setDcTimeSlots(timeSlot.dcSlotMethod(Integer.parseInt(dcslot.getDcTimeSlots()),0));
            dcslot.setMaxTruckNumber(dcslot.getMaxTruckNumber());
            final DcSlots updateddc = dcSlotRespository.save(dcslot);
            return dcSlotRespository.save(updateddc);
        }
        throw new DcSlotNotFoundException("Dc not found for this DcNumber :: " + dcNumber);

    }

    @Override
    public void deleteDcSlot(long dcNumber)
throws DcSlotNotFoundException {
            DcSlots dcSlots = dcSlotRespository.findById(dcNumber)
                    .orElseThrow(() -> new DcSlotNotFoundException("Dc  not found for this id :: " + dcNumber));

            dcSlotRespository.delete(dcSlots);

    }

    @Override
    public List<DcSlots> getAllDcSlots()
    {
        return (List<DcSlots>) dcSlotRespository.findAll();
    }
}
