package practice.service.province;

import practice.model.Province;
import practice.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    IProvinceRepository provinceRepository;

    @Override
    public List<Province> findALl() {
        return (List<Province>) provinceRepository.findAll();
    }

    @Override
    public Page<Province> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public Province save(Province province) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Province> findByName(String name) {
        return null;
    }


}
