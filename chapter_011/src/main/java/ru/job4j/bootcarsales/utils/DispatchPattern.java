package ru.job4j.bootcarsales.utils;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.bootcarsales.domain.AdvertAuto;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DispatchPattern {

    private final Map<String, BiFunction<AdvertAuto, FileItem, AdvertAuto>> dispatch = new HashMap<>();

    public DispatchPattern init() {
        this.load("mark", this.setMark());
        this.load("model", this.setModel());
        this.load("years", this.setYears());
        this.load("bodyType", this.setBodyType());
        this.load("engineType", this.setEngineType());
        this.load("drive", this.setDrive());
        this.load("transmission", this.setTransmission());
        this.load("mileage", this.setMileage());
        this.load("condition", this.setCondition());
        this.load("description", this.setDescription());
        this.load("price", this.setPrice());
        this.load("nameSeller", this.setNameSeller());
        this.load("phoneNumber", this.setPhoneNumber());
        return this;
    }

    public void load(String key, BiFunction<AdvertAuto, FileItem, AdvertAuto> function) {
        this.dispatch.put(key, function);
    }


    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setMark() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setMark(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setModel() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setModel(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setYears() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setYears(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setBodyType() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setBodyType(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setEngineType() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setEngineType(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setDrive() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setDrive(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setTransmission() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setTransmission(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setMileage() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setMileage(Double.parseDouble(f.getString("UTF-8")));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setCondition() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setCondition(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setDescription() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setDescription(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setPrice() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setPrice(Double.parseDouble(f.getString("UTF-8")));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setNameSeller() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setNameSeller(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    private BiFunction<AdvertAuto, FileItem, AdvertAuto> setPhoneNumber() {
        return (a, f) -> {
            try {
                if (!f.getString("UTF-8").equals("")) {
                    a.setPhoneNumber(f.getString("UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return a;
        };
    }

    public void sent(AdvertAuto advertAuto, FileItem item) throws UnsupportedEncodingException {
        if (!item.getString("UTF-8").equals("")) {
            this.dispatch.get(item.getFieldName()).apply(advertAuto, item);
        }
    }


}
